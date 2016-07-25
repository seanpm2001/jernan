//
// Translated by CS2J (http://www.cs2j.com): 25/07/2016 2:46:37 a.m.
//

package Grace.Parsing;


import Grace.Parsing.CommentToken;
import Grace.Parsing.ParseNode;
import Grace.Parsing.ParseNodeVisitor;
import Grace.Parsing.Token;
import java.io.PrintStream;

/**
* Parse node for a comment
*/
public class CommentParseNode  extends ParseNode 
{
    private String _value;
    /**
    * String body of comment
    */
    public String getValue() throws Exception {
        return _value;
    }

    public void setValue(String value) throws Exception {
        _value = value;
    }

    public CommentParseNode(Token tok) throws Exception {
        super(tok);
        CommentToken comm = tok instanceof CommentToken ? (CommentToken)tok : (CommentToken)null;
        _value = comm.getValue();
    }

    /**
    * 
    */
    public void debugPrint(PrintStream tw, String prefix) throws Exception {
        tw.println(prefix + "Comment: " + _value);
        if (this.getComment() != null)
            this.getComment().debugPrint(tw,prefix);
         
    }

    /**
    * 
    */
    public <T>T visit(ParseNodeVisitor<T> visitor) throws Exception {
        return visitor.visit(this);
    }

}


