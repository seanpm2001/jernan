//
// Translated by CS2J (http://www.cs2j.com): 25/07/2016 2:46:37 a.m.
//

package Grace.Parsing;

import java.util.List;
import Grace.Parsing.AliasParseNode;
import Grace.Parsing.BindParseNode;
import Grace.Parsing.BlockParseNode;
import Grace.Parsing.ClassDeclarationParseNode;
import Grace.Parsing.CommentParseNode;
import Grace.Parsing.DefDeclarationParseNode;
import Grace.Parsing.DialectParseNode;
import Grace.Parsing.ExcludeParseNode;
import Grace.Parsing.ExplicitBracketRequestParseNode;
import Grace.Parsing.ExplicitReceiverRequestParseNode;
import Grace.Parsing.IdentifierParseNode;
import Grace.Parsing.ImplicitBracketRequestParseNode;
import Grace.Parsing.ImplicitReceiverRequestParseNode;
import Grace.Parsing.ImportParseNode;
import Grace.Parsing.InheritsParseNode;
import Grace.Parsing.InterpolatedStringParseNode;
import Grace.Parsing.MethodDeclarationParseNode;
import Grace.Parsing.NumberParseNode;
import Grace.Parsing.ObjectParseNode;
import Grace.Parsing.OperatorParseNode;
import Grace.Parsing.OrdinarySignaturePartParseNode;
import Grace.Parsing.ParenthesisedParseNode;
import Grace.Parsing.ParseNode;
import Grace.Parsing.ParseNodeVisitor;
import Grace.Parsing.PrefixOperatorParseNode;
import Grace.Parsing.ReturnParseNode;
import Grace.Parsing.SignatureParseNode;
import Grace.Parsing.StringLiteralParseNode;
import Grace.Parsing.TraitDeclarationParseNode;
import Grace.Parsing.TypedParameterParseNode;
import Grace.Parsing.TypeParseNode;
import Grace.Parsing.TypeStatementParseNode;
import Grace.Parsing.UsesParseNode;
import Grace.Parsing.VarDeclarationParseNode;

/**
* Abstract superclass visiting all nodes suitable for writing
* concrete subclasses that check properties of the parse tree.
*/
public abstract class CheckingParseNodeVisitor   implements ParseNodeVisitor<ParseNode>
{
    /**
    * 
    */
    public ParseNode visit(ParseNode p) throws Exception {
        return p;
    }

    /**
    * 
    */
    public ParseNode visit(ObjectParseNode o) throws Exception {
        for (ParseNode p : o.getBody())
        {
            p.visit(this);
        }
        return o;
    }

    /**
    * 
    */
    public ParseNode visit(NumberParseNode n) throws Exception {
        return n;
    }

    /**
    * 
    */
    public ParseNode visit(MethodDeclarationParseNode d) throws Exception {
        d.getSignature().visit(this);
        for (ParseNode p : d.getBody())
            p.visit(this);
        return d;
    }

    /**
    * 
    */
    public ParseNode visit(IdentifierParseNode i) throws Exception {
        return i;
    }

    /**
    * 
    */
    public ParseNode visit(ImplicitReceiverRequestParseNode irrpn) throws Exception {
        for (List<ParseNode> args : irrpn.getArguments())
            for (ParseNode a : args)
                a.visit(this);
        for (List<ParseNode> args : irrpn.getGenericArguments())
            for (ParseNode a : args)
                a.visit(this);
        return irrpn;
    }

    /**
    * 
    */
    public ParseNode visit(ExplicitReceiverRequestParseNode errpn) throws Exception {
        for (List<ParseNode> args : errpn.getArguments())
            for (ParseNode a : args)
                a.visit(this);
        for (List<ParseNode> args : errpn.getGenericArguments())
            for (ParseNode a : args)
                a.visit(this);
        errpn.getReceiver().visit(this);
        return errpn;
    }

    /**
    * 
    */
    public ParseNode visit(OperatorParseNode opn) throws Exception {
        opn.getLeft().visit(this);
        opn.getRight().visit(this);
        return opn;
    }

    /**
    * 
    */
    public ParseNode visit(TypedParameterParseNode tppn) throws Exception {
        tppn.getType().visit(this);
        return tppn;
    }

    /**
    * 
    */
    public ParseNode visit(StringLiteralParseNode slpn) throws Exception {
        return slpn;
    }

    /**
    * 
    */
    public ParseNode visit(InterpolatedStringParseNode ispn) throws Exception {
        for (ParseNode p : ispn.getParts())
            p.visit(this);
        return ispn;
    }

    /**
    * 
    */
    public ParseNode visit(VarDeclarationParseNode vdpn) throws Exception {
        vdpn.getName().visit(this);
        if (vdpn.getValue() != null)
            vdpn.getValue().visit(this);
         
        if (vdpn.getType() != null)
            vdpn.getType().visit(this);
         
        if (vdpn.getAnnotations() != null)
            vdpn.getAnnotations().visit(this);
         
        return vdpn;
    }

    /**
    * 
    */
    public ParseNode visit(DefDeclarationParseNode vdpn) throws Exception {
        vdpn.getName().visit(this);
        if (vdpn.getValue() != null)
            vdpn.getValue().visit(this);
         
        if (vdpn.getType() != null)
            vdpn.getType().visit(this);
         
        if (vdpn.getAnnotations() != null)
            vdpn.getAnnotations().visit(this);
         
        return vdpn;
    }

    /**
    * 
    */
    public ParseNode visit(BindParseNode bpn) throws Exception {
        bpn.getLeft().visit(this);
        bpn.getRight().visit(this);
        return bpn;
    }

    /**
    * 
    */
    public ParseNode visit(PrefixOperatorParseNode popn) throws Exception {
        popn.getReceiver().visit(this);
        return popn;
    }

    /**
    * 
    */
    public ParseNode visit(BlockParseNode bpn) throws Exception {
        for (ParseNode p : bpn.getParameters())
            p.visit(this);
        for (ParseNode s : bpn.getBody())
            s.visit(this);
        return bpn;
    }

    /**
    * 
    */
    public ParseNode visit(ClassDeclarationParseNode bpn) throws Exception {
        bpn.getSignature().visit(this);
        for (ParseNode s : bpn.getBody())
            s.visit(this);
        return bpn;
    }

    /**
    * 
    */
    public ParseNode visit(TraitDeclarationParseNode bpn) throws Exception {
        bpn.getSignature().visit(this);
        for (ParseNode s : bpn.getBody())
            s.visit(this);
        return bpn;
    }

    /**
    * 
    */
    public ParseNode visit(ReturnParseNode rpn) throws Exception {
        if (rpn.getReturnValue() != null)
            rpn.getReturnValue().visit(this);
         
        return rpn;
    }

    /**
    * 
    */
    public ParseNode visit(CommentParseNode cpn) throws Exception {
        return cpn;
    }

    /**
    * 
    */
    public ParseNode visit(TypeStatementParseNode tspn) throws Exception {
        tspn.getBaseName().visit(this);
        tspn.getBody().visit(this);
        return tspn;
    }

    /**
    * 
    */
    public ParseNode visit(TypeParseNode tpn) throws Exception {
        for (ParseNode t : tpn.getBody())
            t.visit(this);
        return tpn;
    }

    /**
    * 
    */
    public ParseNode visit(ImportParseNode ipn) throws Exception {
        ipn.getPath().visit(this);
        ipn.getName().visit(this);
        return ipn;
    }

    /**
    * 
    */
    public ParseNode visit(DialectParseNode dpn) throws Exception {
        dpn.getPath().visit(this);
        return dpn;
    }

    /**
    * 
    */
    public ParseNode visit(InheritsParseNode ipn) throws Exception {
        ipn.getFrom().visit(this);
        for (AliasParseNode ap : ipn.getAliases())
            ap.visit(this);
        return ipn;
    }

    /**
    * 
    */
    public ParseNode visit(UsesParseNode upn) throws Exception {
        upn.getFrom().visit(this);
        for (AliasParseNode ap : upn.getAliases())
            ap.visit(this);
        return upn;
    }

    /**
    * 
    */
    public ParseNode visit(AliasParseNode ipn) throws Exception {
        ipn.getNewName().visit(this);
        ipn.getOldName().visit(this);
        return ipn;
    }

    /**
    * 
    */
    public ParseNode visit(ExcludeParseNode ipn) throws Exception {
        ipn.getName().visit(this);
        return ipn;
    }

    /**
    * 
    */
    public ParseNode visit(ParenthesisedParseNode ppn) throws Exception {
        ppn.getExpression().visit(this);
        return ppn;
    }

    /**
    * 
    */
    public ParseNode visit(ImplicitBracketRequestParseNode ibrpn) throws Exception {
        for (ParseNode a : ibrpn.getArguments())
            a.visit(this);
        return ibrpn;
    }

    /**
    * 
    */
    public ParseNode visit(ExplicitBracketRequestParseNode ebrpn) throws Exception {
        ebrpn.getReceiver().visit(this);
        for (ParseNode a : ebrpn.getArguments())
            a.visit(this);
        return ebrpn;
    }

    /**
    * 
    */
    public ParseNode visit(SignatureParseNode spn) throws Exception {
        for (SignaturePartParseNode n : spn.getParts())
            n.visit(this);
        return spn;
    }

    /**
    * 
    */
    public ParseNode visit(OrdinarySignaturePartParseNode osppn) throws Exception {
        for (ParseNode p : osppn.getParameters())
            p.visit(this);
        for (ParseNode p : osppn.getGenericParameters())
            p.visit(this);
        return osppn;
    }

}


