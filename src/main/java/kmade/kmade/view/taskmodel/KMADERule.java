package kmade.kmade.view.taskmodel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Locale;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import javax.swing.JComponent;

/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class KMADERule extends JComponent implements Accessible {
    private static final long serialVersionUID = 4508124146765201833L;

    public static final int INCH = Toolkit.getDefaultToolkit().getScreenResolution();

    public static final int HORIZONTAL = 0;

    public static final int VERTICAL = 1;

    public static final int SIZE = 30;

    public int orientation;

    public boolean isMetric;

    private int increment;

    private int units;

    public KMADERule(int o, boolean m) {
        orientation = o;
        isMetric = m;
        setIncrementAndUnits();
    }

    public void setIsMetric(boolean isMetric) {
        if (accessibleContext != null && this.isMetric != isMetric) {
            if (isMetric) {
                accessibleContext.firePropertyChange(
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        AccessibleRulerState.INCHES,
                        AccessibleRulerState.CENTIMETERS);
            } else {
                accessibleContext.firePropertyChange(
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        AccessibleRulerState.CENTIMETERS,
                        AccessibleRulerState.INCHES);
            }
        }
        this.isMetric = isMetric;
        setIncrementAndUnits();
        repaint();
    }

    private void setIncrementAndUnits() {
        if (isMetric) {
            units = (int) ((double) INCH / (double) 2.54); // dots per
            increment = units;
        } else {
            units = INCH;
            increment = units / 2;
        }
    }

    public boolean isMetric() {
        return this.isMetric;
    }

    public int getIncrement() {
        return increment;
    }

    public void setPreferredHeight(int ph) {
        setPreferredSize(new Dimension(SIZE, ph));
    }

    public void setPreferredWidth(int pw) {
        setPreferredSize(new Dimension(pw, SIZE));
    }

    public void paintComponent(Graphics g) {
        Rectangle drawHere = g.getClipBounds();
        g.setColor(new Color(165, 163, 151));
        g.fillRect(drawHere.x, drawHere.y, drawHere.width, drawHere.height);
        g.setFont(new Font("SansSerif", Font.PLAIN, 10));
        g.setColor(Color.black);

        // Some vars we need.
        int end = 0;
        int start = 0;
        int tickLength = 0;
        String text = null;

        // Use clipping bounds to calculate first tick
        // and last tick location.
        if (orientation == HORIZONTAL) {
            start = (drawHere.x / increment) * increment;
            end = (((drawHere.x + drawHere.width) / increment) + 1) * increment;
        } else {
            start = (drawHere.y / increment) * increment;
            end = (((drawHere.y + drawHere.height) / increment) + 1)
                    * increment;
        }

        // Make a special case of 0 to display the number
        // within the rule and draw a units label.
        if (start == 0) {
            text = Integer.toString(0) + (isMetric ? " cm" : " in");
            tickLength = 10;
            if (orientation == HORIZONTAL) {
                g.drawLine(0, SIZE - 1, 0, SIZE - tickLength - 1);
                g.drawString(text, 2, 15);
            } else {
                g.drawLine(SIZE - 1, 0, SIZE - tickLength - 1, 0);
                g.drawString(text, 2, 10);
            }
            text = null;
            start = increment;
        }

        // ticks and labels
        for (int i = start; i < end; i += increment) {
            if (i % units == 0) {
                tickLength = 10;
                text = Integer.toString(i / units);
            } else {
                tickLength = 7;
                text = null;
            }

            if (tickLength != 0) {
                if (orientation == HORIZONTAL) {
                    g.drawLine(i, SIZE - 1, i, SIZE - tickLength - 1);
                    if (text != null)
                        g.drawString(text, i - 3, 15);
                } else {
                    g.drawLine(SIZE - 1, i, SIZE - tickLength - 1, i);
                    if (text != null)
                        g.drawString(text, 2, i + 3);
                }
            }
        }
    }

    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleRuler();
        }
        return accessibleContext;
    }

    protected class AccessibleRuler extends AccessibleJComponent {
        private static final long serialVersionUID = -5438061048697306147L;

        public AccessibleRole getAccessibleRole() {
            return AccessibleRuleRole.RULER;
        }

        public AccessibleStateSet getAccessibleStateSet() {
            AccessibleStateSet states = super.getAccessibleStateSet();
            if (orientation == VERTICAL) {
                states.add(AccessibleState.VERTICAL);
            } else {
                states.add(AccessibleState.HORIZONTAL);
            }
            if (isMetric) {
                states.add(AccessibleRulerState.CENTIMETERS);
            } else {
                states.add(AccessibleRulerState.INCHES);
            }
            return states;
        }
    }
}

class AccessibleRuleRole extends AccessibleRole {
    public static final AccessibleRuleRole RULER = new AccessibleRuleRole("ruler");

    protected AccessibleRuleRole(String key) {
        super(key);
    }

    public String toDisplayString(String resourceBundleName, Locale locale) {
        return key;
    }
}

class AccessibleRulerState extends AccessibleState {
    public static final AccessibleRulerState INCHES = new AccessibleRulerState("inches");

    public static final AccessibleRulerState CENTIMETERS = new AccessibleRulerState("centimeters");

    protected AccessibleRulerState(String key) {
        super(key);
    }

    // Should really provide localizable versions of these names.
    public String toDisplayString(String resourceBundleName, Locale locale) {
        return key;
    }
}
