//
// Translated by CS2J (http://www.cs2j.com): 25/07/2016 2:46:36 a.m.
//

package Grace.Parsing;


import Grace.Parsing.NumberToken;
import Grace.Parsing.ParseNode;
import Grace.Parsing.ParseNodeVisitor;
import Grace.Parsing.Token;
import java.io.PrintStream;

/**
* Parse node for a number
*/
public class NumberParseNode  extends ParseNode 
{
    private int _base;
    /**
    * Base of the number
    */
    public int getNumericBase() throws Exception {
        return _base;
    }

    public void setNumericBase(int value) throws Exception {
        _base = value;
    }

    private String _digits;
    /**
    * Digits of the number in its base
    */
    public String getDigits() throws Exception {
        return _digits;
    }

    public void setDigits(String value) throws Exception {
        _digits = value;
    }

    public NumberParseNode(Token tok) throws Exception {
        super(tok);
        NumberToken it = tok instanceof NumberToken ? (NumberToken)tok : (NumberToken)null;
        _base = it.getNumericBase();
        _digits = it.getDigits();
    }

    /**
    * 
    */
    public void debugPrint(PrintStream tw, String prefix) throws Exception {
        String desc = "";
        if (_base == 10)
            desc += _digits;
        else if (_base == 16)
            desc += "0x" + _digits;
        else
            desc += _base + "x" + _digits;  
        tw.println(prefix + "Number: " + desc);
        writeComment(tw,prefix);
    }

    /**
    * 
    */
    public <T>T visit(ParseNodeVisitor<T> visitor) throws Exception {
        return visitor.visit(this);
    }

}


