package kmade.kmade.adaptatorFC.parserExpression;

import java.io.InputStream;
import java.io.Reader;

import kmade.kmade.KMADEConstant;

public class MyEffetsDeBord extends EffetsDeBord{
	
	public MyEffetsDeBord(EffetsDeBordTokenManager tm) {
		super(tm);
	}
	public MyEffetsDeBord(InputStream stream, String encoding) {
		super(stream, encoding);
	}

	public MyEffetsDeBord(InputStream stream) {
		super(stream);
	}

	public MyEffetsDeBord(Reader stream) {
		super(stream);
	}

	  public ParseException generateParseException() {
		  Token errortok = token.next;
	      int line = errortok.beginLine, column = errortok.beginColumn;
	      String mess = (errortok.kind == 0) ? tokenImage[0] : errortok.image;
	      return new ParseException(KMADEConstant.LINE_MESSAGE + " " + line + KMADEConstant.COLUMN_MESSAGE + " " + column + ", " + KMADEConstant.ENCOUNTERED_TOKEN_MESSAGE + "\"" + mess + "\"");
	  }

}