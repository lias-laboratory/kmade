/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
 * 
 * KMADe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * KMADe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with KMADe.  If not, see <http://www.gnu.org/licenses/>.
 **********************************************************************************/
package fr.upensma.lias.kmade;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * JDK 6's {@link ResourceBundle.Control} subclass that allows loading of
 * bundles in XML format. The bundles are searched first as Java classes, then
 * as properties files (these two methods are the standard search mechanism of
 * ResourceBundle), then as XML properties files. The filename extension of the
 * XML properties files is assumed to be *.properties.xml
 */
public class ExtendedControl extends ResourceBundle.Control {

	private static final String FORMAT_XML_SUFFIX = "properties.xml";

	private static final String FORMAT_XML = "java." + FORMAT_XML_SUFFIX;

	private static final List<String> FORMATS;

	static {
		List<String> formats = new ArrayList<String>(FORMAT_DEFAULT);
		formats.add(FORMAT_XML);
		FORMATS = Collections.unmodifiableList(formats);
	}

	@Override
	public List<String> getFormats(String baseName) {
		return FORMATS;
	}

	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {
		if (!FORMAT_XML.equals(format)) {
			return super.newBundle(baseName, Locale.ENGLISH, format, loader, reload);
		}

		String bundleName = toBundleName(baseName, locale);
		String resourceName = toResourceName(bundleName, FORMAT_XML_SUFFIX);
		final URL resourceURL = loader.getResource(resourceName);
		if (resourceURL == null)
			return null;

		InputStream stream = getResourceInputStream(resourceURL, reload);

		try {
			PropertyXMLResourceBundle result = new PropertyXMLResourceBundle();
			result.load(stream);
			return result;
		} finally {
			stream.close();
		}
	}

	private InputStream getResourceInputStream(final URL resourceURL, boolean reload) throws IOException {
		if (!reload)
			return resourceURL.openStream();

		try {
			// This permission has already been checked by
			// ClassLoader.getResource(String), which will return null
			// in case the code has not enough privileges.
			return AccessController.doPrivileged(new PrivilegedExceptionAction<InputStream>() {
				public InputStream run() throws IOException {
					URLConnection connection = resourceURL.openConnection();
					connection.setUseCaches(false);
					return connection.getInputStream();
				}
			});
		} catch (PrivilegedActionException x) {
			throw (IOException) x.getCause();
		}
	}

	/**
	 * ResourceBundle that loads definitions from an XML properties file.
	 */
	public static class PropertyXMLResourceBundle extends ResourceBundle {
		private final Properties properties = new Properties();

		public void load(InputStream stream) throws IOException {
			properties.loadFromXML(stream);
		}

		protected Object handleGetObject(String key) {
			return properties.getProperty(key);
		}

		public Enumeration<String> getKeys() {
			final Enumeration<Object> keys = properties.keys();
			return new Enumeration<String>() {
				public boolean hasMoreElements() {
					return keys.hasMoreElements();
				}

				public String nextElement() {
					return (String) keys.nextElement();
				}
			};
		}
	}
}
