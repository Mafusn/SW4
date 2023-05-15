import AST.Nodes.*;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void IntDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/intDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        IntDcl intDcl = new IntDcl("phone_number");
        IntDcl intDcl2 = new IntDcl("creditCardNumber");
        IntDcl intDcl3 = new IntDcl("a123456");
        expectedAST.addChild(intDcl);
        expectedAST.addChild(intDcl2);
        expectedAST.addChild(intDcl3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void FloatDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/floatDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        FloatDcl floatDcl = new FloatDcl("floatDcl");
        FloatDcl floatDcl2 = new FloatDcl("my_variable");
        FloatDcl floatDcl3 = new FloatDcl("order123");
        expectedAST.addChild(floatDcl);
        expectedAST.addChild(floatDcl2);
        expectedAST.addChild(floatDcl3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void BoolDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/boolDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        BoolDcl boolDcl = new BoolDcl("fooBar");
        BoolDcl boolDcl2 = new BoolDcl("thisIsA_boolean2023");
        BoolDcl boolDcl3 = new BoolDcl("boolean_value");
        expectedAST.addChild(boolDcl);
        expectedAST.addChild(boolDcl2);
        expectedAST.addChild(boolDcl3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void MixDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/mixDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        BoolDcl boolDcl = new BoolDcl("fooBar");
        FloatDcl floatDcl = new FloatDcl("thisIsA_boolean2023");
        IntDcl intDcl = new IntDcl("a123456");
        expectedAST.addChild(boolDcl);
        expectedAST.addChild(floatDcl);
        expectedAST.addChild(intDcl);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void WrongTypeDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/wrongTypeDcl.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.Prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }

    @Test
    void SpecialCharDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/containsSpecialCharDcl.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.Prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }

    @Test
    void AssignIntTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignInt.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        Id id = new Id("a");
        Id id2 = new Id("b");
        IntNum intVal = new IntNum("5");
        IntNum intVal2 = new IntNum("10");
        AssignmentOp intAssign = new AssignmentOp("a",id, intVal);
        AssignmentOp intAssign2 = new AssignmentOp("b",id2, intVal2);
        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignIntWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignIntWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        Id id = new Id("a");
        Id id2 = new Id("b");
        Id id3 = new Id("c");
        IntNum intVal = new IntNum("5");
        IntNum intVal2 = new IntNum("10");
        IntNum intVal3 = new IntNum("10");
        AssignmentOp intAssign = new AssignmentOp("a",id, intVal);
        AssignmentOp intAssign2 = new AssignmentOp("b",id2, intVal2);
        AssignmentOp intAssign3 = new AssignmentOp("c",id3, intVal3);

        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);
        expectedAST.addChild(intAssign3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignFloatTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignFloat.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        Id id = new Id("a");
        Id id2 = new Id("b");
        FloatNum floatVal = new FloatNum("3.14");
        FloatNum floatVal2 = new FloatNum("0.025");
        AssignmentOp floatAssign = new AssignmentOp("a",id, floatVal);
        AssignmentOp floatAssign2 = new AssignmentOp("b",id2, floatVal2);
        expectedAST.addChild(floatAssign);
        expectedAST.addChild(floatAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignBoolTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignBool.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        Id id = new Id("a");
        Id id2 = new Id("b");
        Bool boolVal = new Bool("true");
        Bool boolVal2 = new Bool("false");
        AssignmentOp boolAssign = new AssignmentOp("a",id, boolVal);
        AssignmentOp boolAssign2 = new AssignmentOp("b",id2, boolVal2);
        expectedAST.addChild(boolAssign);
        expectedAST.addChild(boolAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignIntDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignIntDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        IntDcl intDcl = new IntDcl("a");
        IntDcl intDcl2 = new IntDcl("b");
        IntNum intVal = new IntNum("3");
        IntNum intVal2 = new IntNum("12");
        AssignmentOp intAssign = new AssignmentOp("a",intDcl, intVal);
        AssignmentOp intAssign2 = new AssignmentOp("b",intDcl2, intVal2);
        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignFloatDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignFloatDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        FloatDcl floatDcl = new FloatDcl("a");
        FloatDcl floatDcl2 = new FloatDcl("b");
        FloatNum floatVal = new FloatNum("8.32");
        FloatNum floatVal2 = new FloatNum("0.97");
        AssignmentOp floatAssign = new AssignmentOp("a",floatDcl, floatVal);
        AssignmentOp floatAssign2 = new AssignmentOp("b",floatDcl2, floatVal2);
        expectedAST.addChild(floatAssign);
        expectedAST.addChild(floatAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignBoolDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignBoolDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        BoolDcl boolDcl = new BoolDcl("p");
        BoolDcl boolDcl2 = new BoolDcl("q");
        Bool boolVal = new Bool("true");
        Bool boolVal2 = new Bool("false");
        AssignmentOp boolAssign = new AssignmentOp("p",boolDcl, boolVal);
        AssignmentOp boolAssign2 = new AssignmentOp("q",boolDcl2, boolVal2);
        expectedAST.addChild(boolAssign);
        expectedAST.addChild(boolAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void MixAssignDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/mixAssignWithDcls.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        IntDcl intDcl = new IntDcl("a");
        BoolDcl boolDcl = new BoolDcl("b");
        FloatDcl floatDcl = new FloatDcl("c");
        IntNum intVal = new IntNum("1");
        Bool boolVal = new Bool("false");
        FloatNum floatVal = new FloatNum("6.2");
        AssignmentOp intAssign = new AssignmentOp("a", intDcl, intVal);
        AssignmentOp boolAssign = new AssignmentOp("b", boolDcl, boolVal);
        AssignmentOp floatAssign = new AssignmentOp("c", floatDcl, floatVal);
        expectedAST.addChild(intAssign);
        expectedAST.addChild(boolAssign);
        expectedAST.addChild(floatAssign);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void WrongAssignTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/wrongAssign.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.Prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }


    @Test
    void IfTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/if.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        IntNum intNum = new IntNum("2");
        IntNum intNum2 = new IntNum("4");
        ComparisonOp compOp = new ComparisonOp("<", intNum, intNum2);
        Id id = new Id("t");
        Bool bool = new Bool("true");
        AssignmentOp assigning = new AssignmentOp("t", id, bool);
        Block block = new Block();
        block.addChild(assigning);
        IfStmt ifStmt = new IfStmt (compOp, block);
        expectedAST.addChild(ifStmt);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void IfElseTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/ifElse.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        IntNum intNum = new IntNum("1");
        IntNum intNum2 = new IntNum("2");
        ComparisonOp comOp = new ComparisonOp(">", intNum, intNum2);
        Id id = new Id("t");
        Bool bool = new Bool("true");
        Bool bool2 = new Bool ("false");
        AssignmentOp assigning = new AssignmentOp("t", id, bool);
        AssignmentOp assigning2 = new AssignmentOp("t", id, bool2);
        Block block = new Block();
        block.addChild(assigning);
        Block block2 = new Block();
        block2.addChild(assigning2);
        IfElseStmt ifElseStmt = new IfElseStmt(comOp, block, block2);
        expectedAST.addChild(ifElseStmt);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void WrongIfElseTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/wrongIfElse.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.Prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }

    @Test
    void NestedIfElseTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/nestedIfElse.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        // elseBlock
        Id id = new Id("b");
        FloatNum floatNum = new FloatNum("3.5");
        AssignmentOp assigning = new AssignmentOp("b", id, floatNum);
        Block elseBlock = new Block();
        elseBlock.addChild(assigning);

        Block ifBlockInner = new Block();

        // condition for if-else statement
        Bool boolVal = new Bool("true");
        Bool boolVal2 = new Bool("false");
        ComparisonOp comOp2 = new ComparisonOp("==", boolVal, boolVal2);

        IfElseStmt ifElseStmt = new IfElseStmt(comOp2, ifBlockInner, elseBlock);

        Block ifBlockOuter = new Block();
        ifBlockOuter.addChild(ifElseStmt);

        // condition for outer if-statement
        IntNum intNum = new IntNum("1");
        IntNum intNum2 = new IntNum("2");
        ComparisonOp comOp = new ComparisonOp("<", intNum, intNum2);

        IfStmt ifStmt = new IfStmt(comOp, ifBlockOuter);
        
        expectedAST.addChild(ifStmt);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.Prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    private Compiler readFileToParse(String filePath) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(filePath);
        CompilerTokenManager tm = new CompilerTokenManager(new SimpleCharStream(stream));
        return new Compiler(tm);
    }
}