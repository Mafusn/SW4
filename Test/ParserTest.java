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
            Prog AST = (Prog) compiler.prog();
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
            Prog AST = (Prog) compiler.prog();
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
            Prog AST = (Prog) compiler.prog();
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
            Prog AST = (Prog) compiler.prog();
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
            compiler.prog();
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
            compiler.prog();
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
        Assigning intAssign = new Assigning("a", new Id("a", false), new IntNum("5"));
        Assigning intAssign2 = new Assigning("b", new Id("b", false), new IntNum("10"));
        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.prog();
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
        Computing computingA1 = new Computing("-", new IntNum("3"), new IntNum("4"));
        Computing computingA2 = new Computing("-", new IntNum("5"), computingA1);
        Computing computingA3 = new Computing("+", new IntNum("4"), computingA2);
        Computing computingA4 = new Computing("+", new IntNum("5"), new IntNum("10"));
        Computing computingA5 = new Computing("-", computingA4, computingA3);
        Computing computingA6 = new Computing("+", new IntNum("5"), computingA5);
        Computing computingB1 = new Computing("-", new IntNum("10"), new IntNum("5"));
        Computing computingC1 = new Computing("+", new IntNum("0"), new IntNum("10005"));
        Assigning intAssign = new Assigning("a", new Id("a", false), computingA6);
        Assigning intAssign2 = new Assigning("b",new Id("b", false), computingB1);
        Assigning intAssign3 = new Assigning("c",new Id("c", false), computingC1);

        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);
        expectedAST.addChild(intAssign3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignFloatWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignFloatWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        Computing computingA1 = new Computing("-", new FloatNum("3.2"), new FloatNum("4.3562"));
        Computing computingA2 = new Computing("-", new FloatNum("5.42"), computingA1);
        Computing computingA3 = new Computing("+", new FloatNum("4.92"), computingA2);
        Computing computingA4 = new Computing("+", new FloatNum("5.00005"), new FloatNum("10.4"));
        Computing computingA5 = new Computing("-", computingA4, computingA3);
        Computing computingA6 = new Computing("+", new FloatNum("5.0"), computingA5);
        Computing computingB1 = new Computing("-", new FloatNum("10.0"), new FloatNum("5.43"));
        Computing computingC1 = new Computing("+", new FloatNum("0.3"), new FloatNum("10005.78"));
        Assigning floatAssign = new Assigning("a", new Id("a", false), computingA6);
        Assigning floatAssign2 = new Assigning("b",new Id("b", false), computingB1);
        Assigning floatAssign3 = new Assigning("c",new Id("c", false), computingC1);

        expectedAST.addChild(floatAssign);
        expectedAST.addChild(floatAssign2);
        expectedAST.addChild(floatAssign3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignBoolWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignBoolWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        BinOperator binOperatorA1 = new BinOperator("||", new Bool("true"), new Bool("false"));
        BinOperator binOperatorA2 = new BinOperator("&&", new Bool("true"), new Not(new Id("x", false)));
        BinOperator binOperatorA3 = new BinOperator("&&", binOperatorA1, binOperatorA2);
        BinOperator binOperatorB1 = new BinOperator("||", new Bool("true"), new Bool("false"));
        BinOperator binOperatorC1 = new BinOperator("&&", new Bool("false"), new Bool("true"));
        BinOperator binOperatorD1 = new BinOperator("<", new FloatNum("2.5"), new FloatNum("4.3"));
        BinOperator binOperatorD2 = new BinOperator("&&", binOperatorD1, new Bool("true"));
        BinOperator binOperatorE1 = new BinOperator(">", new IntNum("5"), new IntNum("5"));
        BinOperator binOperatorE2 = new BinOperator("<=", new IntNum("4"), binOperatorE1);
        BinOperator binOperatorE3 = new BinOperator("<", new IntNum("2"), binOperatorE2);
        BinOperator binOperatorE4 = new BinOperator(">=", new IntNum("5"), new IntNum("40"));
        BinOperator binOperatorE5 = new BinOperator("!=", new IntNum("5"), new IntNum("10"));
        BinOperator binOperatorE6 = new BinOperator("==", binOperatorE4, binOperatorE5);
        BinOperator binOperatorE7 = new BinOperator("&&", binOperatorE3, binOperatorE6);
        Assigning boolAssign = new Assigning("a", new Id("a", false), binOperatorA3);
        Assigning boolAssign2 = new Assigning("b",new Id("b", false), binOperatorB1);
        Assigning boolAssign3 = new Assigning("c",new Id("c", false), binOperatorC1);
        Assigning boolAssign4 = new Assigning("d",new Id("d", false), binOperatorD2);
        Assigning boolAssign5 = new Assigning("e",new Id("e", false), binOperatorE7);

        expectedAST.addChild(boolAssign);
        expectedAST.addChild(boolAssign2);
        expectedAST.addChild(boolAssign3);
        expectedAST.addChild(boolAssign4);
        expectedAST.addChild(boolAssign5);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignIntDclWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignIntDclWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        Computing computingA1 = new Computing("-", new IntNum("3"), new IntNum("4"));
        Computing computingA2 = new Computing("-", new IntNum("5"), computingA1);
        Computing computingA3 = new Computing("+", new IntNum("4"), computingA2);
        Computing computingA4 = new Computing("+", new IntNum("5"), new IntNum("10"));
        Computing computingA5 = new Computing("-", computingA4, computingA3);
        Computing computingA6 = new Computing("+", new IntNum("5"), computingA5);
        Computing computingB1 = new Computing("-", new IntNum("10"), new IntNum("5"));
        Computing computingC1 = new Computing("+", new IntNum("0"), new IntNum("10005"));
        Assigning intAssign = new Assigning("a", new IntDcl("a"), computingA6);
        Assigning intAssign2 = new Assigning("b",new IntDcl("b"), computingB1);
        Assigning intAssign3 = new Assigning("c",new IntDcl("c"), computingC1);

        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);
        expectedAST.addChild(intAssign3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignFloatDclWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignFloatDclWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        Computing computingA1 = new Computing("-", new FloatNum("3.2"), new FloatNum("4.3562"));
        Computing computingA2 = new Computing("-", new FloatNum("5.42"), computingA1);
        Computing computingA3 = new Computing("+", new FloatNum("4.92"), computingA2);
        Computing computingA4 = new Computing("+", new FloatNum("5.00005"), new FloatNum("10.4"));
        Computing computingA5 = new Computing("-", computingA4, computingA3);
        Computing computingA6 = new Computing("+", new FloatNum("5.0"), computingA5);
        Computing computingB1 = new Computing("-", new FloatNum("10.0"), new FloatNum("5.43"));
        Computing computingC1 = new Computing("+", new FloatNum("0.3"), new FloatNum("10005.78"));
        Assigning floatAssign = new Assigning("a", new FloatDcl("a"), computingA6);
        Assigning floatAssign2 = new Assigning("b",new FloatDcl("b"), computingB1);
        Assigning floatAssign3 = new Assigning("c",new FloatDcl("c"), computingC1);

        expectedAST.addChild(floatAssign);
        expectedAST.addChild(floatAssign2);
        expectedAST.addChild(floatAssign3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignBoolDclWithExprTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignBoolDclWithExpr.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();

        BinOperator binOperatorA1 = new BinOperator("||", new Bool("true"), new Bool("false"));
        BinOperator binOperatorA2 = new BinOperator("&&", new Bool("true"), new Not(new Id("x", false)));
        BinOperator binOperatorA3 = new BinOperator("&&", binOperatorA1, binOperatorA2);
        BinOperator binOperatorB1 = new BinOperator("||", new Bool("true"), new Bool("false"));
        BinOperator binOperatorC1 = new BinOperator("&&", new Bool("false"), new Bool("true"));
        BinOperator binOperatorD1 = new BinOperator("<", new FloatNum("2.5"), new FloatNum("4.3"));
        BinOperator binOperatorD2 = new BinOperator("&&", binOperatorD1, new Bool("true"));
        BinOperator binOperatorE1 = new BinOperator(">", new IntNum("5"), new IntNum("5"));
        BinOperator binOperatorE2 = new BinOperator("<=", new IntNum("4"), binOperatorE1);
        BinOperator binOperatorE3 = new BinOperator("<", new IntNum("2"), binOperatorE2);
        BinOperator binOperatorE4 = new BinOperator(">=", new IntNum("5"), new IntNum("40"));
        BinOperator binOperatorE5 = new BinOperator("!=", new IntNum("5"), new IntNum("10"));
        BinOperator binOperatorE6 = new BinOperator("==", binOperatorE4, binOperatorE5);
        BinOperator binOperatorE7 = new BinOperator("&&", binOperatorE3, binOperatorE6);
        Assigning boolAssign = new Assigning("a", new BoolDcl("a"), binOperatorA3);
        Assigning boolAssign2 = new Assigning("b", new BoolDcl("b"), binOperatorB1);
        Assigning boolAssign3 = new Assigning("c", new BoolDcl("c"), binOperatorC1);
        Assigning boolAssign4 = new Assigning("d", new BoolDcl("d"), binOperatorD2);
        Assigning boolAssign5 = new Assigning("e", new BoolDcl("e"), binOperatorE7);

        expectedAST.addChild(boolAssign);
        expectedAST.addChild(boolAssign2);
        expectedAST.addChild(boolAssign3);
        expectedAST.addChild(boolAssign4);
        expectedAST.addChild(boolAssign5);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertTrue(AST.equals(expectedAST));
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert false;
        }
    }

    @Test
    void AssignWithExprFail() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignWithExprFail.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }

    @Test
    void AssignDclWithExprFail() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignDclWithExprFail.txt");

        // [WHEN] [THEN] We try to parse the code from the file, and it gives an error
        try {
            compiler.prog();
            assert false;
        } catch (Throwable e) {
            System.out.println("Syntax error: " + e.getMessage());
            assert true;
        }
    }

    @Test
    void AssignPointerWithAddressTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignPointerWithAddress.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        Assigning pointerAssign = new Assigning("ptr", new Id("ptr", true), new Id("a", true));
        Assigning pointerAssign2 = new Assigning("ptr2", new Id("ptr2", true), new Id("example", true));
        Assigning pointerAssign3 = new Assigning("ptr3", new Id("ptr3", true), new Id("test", true));
        expectedAST.addChild(pointerAssign);
        expectedAST.addChild(pointerAssign2);
        expectedAST.addChild(pointerAssign3);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.prog();
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