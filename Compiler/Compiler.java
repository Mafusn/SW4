/* Compiler.java */
/* Generated By:JavaCC: Do not edit this line. Compiler.java */
import AST.*;
import AST.CodeGeneration.CodeGenerator;
import AST.Nodes.*;
import AST.SymbolTableFilling.SymbolTableFilling;
import java.util.ArrayList;

public class Compiler implements CompilerConstants {
    public static void main(String[] args) {
        try {
            Compiler compiler = new Compiler(new java.io.FileReader("Compiler/code.txt"));
            Node prog = compiler.Prog();

            PrettyPrint prettyPrint = new PrettyPrint();
            ArrayList<SymbolTableFilling> symbolTableFillings = new ArrayList<>();
            SymbolTableFilling symbolTableFilling = new SymbolTableFilling(symbolTableFillings , 0);
            TypeChecking typeChecking = new TypeChecking(symbolTableFillings);
            ConstantFolding constantFolding = new ConstantFolding();
            CodeGenerator codeGenerator = new CodeGenerator(symbolTableFillings);

            prog.accept(prettyPrint);
            System.out.println(prettyPrint.getResult());
            System.out.println();
            System.out.print("Syntax is good\n");
            prog.accept(symbolTableFilling);
            System.out.println("Symbol table is good");
            prog.accept(typeChecking);
            System.out.println("Type check is good");
            prog.accept(constantFolding);
            prog.accept(codeGenerator);
            System.out.println("Code generator is good");

            codeGenerator.generateCode();

        } catch (Throwable e) {
            // Catching Throwable is ugly but JavaCC throws Error objects!
            System.out.println("Syntax error: " + e.getMessage());
        }
    }

  final public Node Prog() throws ParseException {Prog prog = new Prog();
 Node stmt = null;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INTDCL:
      case FLOATDCL:
      case IF:
      case BOOLDCL:
      case WHILE:
      case ID:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      stmt = Stmt();
prog.addChild(stmt);
    }
    jj_consume_token(END_OF_FILE);
    jj_consume_token(END_OF_LINE);
{if ("" != null) return prog;}
    throw new Error("Missing return statement in function");
}

  final public Node Stmt() throws ParseException {Node ifStmt;
    Node dcl;
    Node expr;
    Node whileLoop;
    Token t;
    Token compAssOp = null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ID:{
      t = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case PLUS:{
          compAssOp = jj_consume_token(PLUS);
          break;
          }
        case MINUS:{
          compAssOp = jj_consume_token(MINUS);
          break;
          }
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        ;
      }
      jj_consume_token(ASSIGN);
      expr = Expr();
      jj_consume_token(END_OF_LINE);
Id id = new Id(t.image);

    if (compAssOp != null) {
        {if ("" != null) return new AssignmentOp(t.image, id, expr, compAssOp.image);}
    } else {
        {if ("" != null) return new AssignmentOp(t.image, id, expr);}
    }
      break;
      }
    case INTDCL:
    case FLOATDCL:
    case BOOLDCL:{
      dcl = Dcl();
      jj_consume_token(END_OF_LINE);
{if ("" != null) return dcl;}
      break;
      }
    case IF:{
      ifStmt = IfStmt();
{if ("" != null) return ifStmt;}
      break;
      }
    case WHILE:{
      whileLoop = WhileLoop();
{if ("" != null) return whileLoop;}
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public Node Dcl() throws ParseException {boolean hasExpr = false;
 Node expr = null;
 Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case FLOATDCL:{
      jj_consume_token(FLOATDCL);
      t = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASSIGN:{
        jj_consume_token(ASSIGN);
        expr = Expr();
hasExpr = true;
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        ;
      }
if (hasExpr) {
       FloatDcl floatDcl = new FloatDcl(t.image);
       {if ("" != null) return new AssignmentOp(t.image, floatDcl, expr);}
   } else {
       {if ("" != null) return new FloatDcl(t.image);}
   }
      break;
      }
    case INTDCL:{
      jj_consume_token(INTDCL);
      t = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASSIGN:{
        jj_consume_token(ASSIGN);
        expr = Expr();
hasExpr = true;
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        ;
      }
if (hasExpr) {
        IntDcl intDcl = new IntDcl(t.image);
        {if ("" != null) return new AssignmentOp(t.image, intDcl, expr);}
    } else {
        {if ("" != null) return new IntDcl(t.image);}
    }
      break;
      }
    case BOOLDCL:{
      jj_consume_token(BOOLDCL);
      t = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASSIGN:{
        jj_consume_token(ASSIGN);
        expr = Expr();
hasExpr = true;
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        ;
      }
if (hasExpr) {
        BoolDcl boolDcl = new BoolDcl(t.image);
        {if ("" != null) return new AssignmentOp(t.image, boolDcl, expr);}
    } else {
        {if ("" != null) return new BoolDcl(t.image);}
    }
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public Node IfStmt() throws ParseException {Node expr;
 Node ifBlock;
 Node elseBlock = null;
 boolean withElse = false;
    jj_consume_token(IF);
    jj_consume_token(LPAREN);
    expr = Expr();
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    ifBlock = Block();
    jj_consume_token(RBRACE);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ELSE:{
      jj_consume_token(ELSE);
      jj_consume_token(LBRACE);
      elseBlock = Block();
      jj_consume_token(RBRACE);
withElse = true;
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      ;
    }
if (withElse) {
        {if ("" != null) return new IfElseStmt(expr, ifBlock, elseBlock);}
    } else {
        {if ("" != null) return new IfStmt(expr, ifBlock);}
    }
    throw new Error("Missing return statement in function");
}

  final public Node WhileLoop() throws ParseException {Node expr;
    Node block;
    jj_consume_token(WHILE);
    jj_consume_token(LPAREN);
    expr = Expr();
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    block = Block();
    jj_consume_token(RBRACE);
{if ("" != null) return new WhileLoop(expr, block);}
    throw new Error("Missing return statement in function");
}

  final public Node Block() throws ParseException {Block block = new Block();
   Node stmt = null;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INTDCL:
      case FLOATDCL:
      case IF:
      case BOOLDCL:
      case WHILE:
      case ID:{
        ;
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        break label_2;
      }
      stmt = Stmt();
block.addChild(stmt);
    }
{if ("" != null) return block;}
    throw new Error("Missing return statement in function");
}

  final public Node Expr() throws ParseException {Node expr;
    expr = OrOp();
{if ("" != null) return expr;}
    throw new Error("Missing return statement in function");
}

  final public Node OrOp() throws ParseException {Node left = null;
    Token op = null;
    Node right = null;
    left = AndOp();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OR:{
        ;
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        break label_3;
      }
      op = jj_consume_token(OR);
      right = AndOp();
left = new ComparisonOp(left, op.image, right);
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  final public Node AndOp() throws ParseException {Node left = null;
    Token op = null;
    Node right = null;
    left = EqualityOp();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        ;
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        break label_4;
      }
      op = jj_consume_token(AND);
      right = EqualityOp();
left = new ComparisonOp(left, op.image, right);
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  final public Node EqualityOp() throws ParseException {Node left = null;
    Token op = null;
    Node right = null;
    left = ComparisonOp();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQ:
      case NE:{
        ;
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQ:{
        op = jj_consume_token(EQ);
        break;
        }
      case NE:{
        op = jj_consume_token(NE);
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = ComparisonOp();
left = new ComparisonOp(left, op.image, right);
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  final public Node ComparisonOp() throws ParseException {Node left = null;
    Token op = null;
    Node right = null;
    left = ArithmeticOp();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LT:
      case LE:
      case GT:
      case GE:{
        ;
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LT:{
        op = jj_consume_token(LT);
        break;
        }
      case GT:{
        op = jj_consume_token(GT);
        break;
        }
      case GE:{
        op = jj_consume_token(GE);
        break;
        }
      case LE:{
        op = jj_consume_token(LE);
        break;
        }
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = ArithmeticOp();
left = new ComparisonOp(left, op.image, right);
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  final public Node ArithmeticOp() throws ParseException {Node left = null;
    Token op = null;
    Node right = null;
    left = NegationOp();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        break label_7;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        op = jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        op = jj_consume_token(MINUS);
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = NegationOp();
left = new ArithmeticOp(left, op.image, right);
    }
{if ("" != null) return left;}
    throw new Error("Missing return statement in function");
}

  final public Node NegationOp() throws ParseException {Node factor;
    boolean hasNegationOp = false;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NOT:{
      jj_consume_token(NOT);
hasNegationOp = true;
      break;
      }
    default:
      jj_la1[18] = jj_gen;
      ;
    }
    factor = Factor();
if (hasNegationOp) {
        {if ("" != null) return new NegationOp(factor);}
    } else {
        {if ("" != null) return factor;}
    }
    throw new Error("Missing return statement in function");
}

  final public Node Factor() throws ParseException {Node val;
    Node expr;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LPAREN:{
      jj_consume_token(LPAREN);
      expr = Expr();
      jj_consume_token(RPAREN);
{if ("" != null) return expr;}
      break;
      }
    case INT:
    case FLOAT:
    case TRUE:
    case FALSE:
    case ID:{
      val = Val();
{if ("" != null) return val;}
      break;
      }
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public Node Val() throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:{
      t = jj_consume_token(INT);
{if ("" != null) return new IntNum(Integer.parseInt(t.image));}
      break;
      }
    case FLOAT:{
      t = jj_consume_token(FLOAT);
{if ("" != null) return new FloatNum(Float.parseFloat(t.image));}
      break;
      }
    case ID:{
      t = jj_consume_token(ID);
{if ("" != null) return new Id(t.image);}
      break;
      }
    case TRUE:
    case FALSE:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TRUE:{
        t = jj_consume_token(TRUE);
        break;
        }
      case FALSE:{
        t = jj_consume_token(FALSE);
        break;
        }
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
{if ("" != null) return new Bool(Boolean.parseBoolean(t.image));}
      break;
      }
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  /** Generated Token Manager. */
  public CompilerTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[22];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x2004140,0x1800,0x1800,0x2004140,0x10000000,0x10000000,0x10000000,0x2000140,0x8000,0x2004140,0x20000,0x10000,0x180000,0x180000,0x1e00000,0x1e00000,0x1800,0x1800,0x40000,0x2c000280,0xc000000,0xc000280,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x30,0x0,0x0,0x30,0x0,0x0,0x0,0x0,0x0,0x30,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x20,0x0,0x20,};
	}

  /** Constructor with InputStream. */
  public Compiler(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Compiler(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new CompilerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Compiler(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new CompilerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new CompilerTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Compiler(CompilerTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CompilerTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[38];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 22; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 38; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
