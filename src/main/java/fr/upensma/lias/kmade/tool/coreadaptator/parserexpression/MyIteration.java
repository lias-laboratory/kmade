package fr.upensma.lias.kmade.tool.coreadaptator.parserexpression;

import java.io.InputStream;
import java.io.Reader;

import fr.upensma.lias.kmade.tool.KMADEConstant;


public class MyIteration extends Iteration{
	
	public MyIteration(IterationTokenManager tm) {
		super(tm);
	}
	public MyIteration(InputStream stream, String encoding) {
		super(stream, encoding);
	}

	public MyIteration(InputStream stream) {
		super(stream);
	}

	public MyIteration(Reader stream) {
		super(stream);
	}

	  public ParseException generateParseException() {
	      Token errortok = token.next;
	      int line = errortok.beginLine, column = errortok.beginColumn;
	      String mess = (errortok.kind == 0) ? tokenImage[0] : errortok.image;
	      return new ParseException(KMADEConstant.LINE_MESSAGE + " " + line + KMADEConstant.COLUMN_MESSAGE + " " + column + ", " + KMADEConstant.ENCOUNTERED_TOKEN_MESSAGE + "\"" + mess + "\"");
	  }

}
