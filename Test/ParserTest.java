import AST.Nodes.*;
import org.junit.jupiter.api.Assertions.*;
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
        Id id = new Id("a");
        Id id2 = new Id("b");
        IntNum intVal = new IntNum("5");
        IntNum intVal2 = new IntNum("10");
        Assigning intAssign = new Assigning("a",id, intVal);
        Assigning intAssign2 = new Assigning("b",id2, intVal2);
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
        Id id = new Id("a");
        Id id2 = new Id("b");
        Id id3 = new Id("c");
        IntNum intVal = new IntNum("5");
        IntNum intVal2 = new IntNum("10");
        IntNum intVal3 = new IntNum("10");
        Assigning intAssign = new Assigning("a",id, intVal);
        Assigning intAssign2 = new Assigning("b",id2, intVal2);
        Assigning intAssign3 = new Assigning("c",id3, intVal3);

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
    void AssignFloatTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignFloat.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        Id id = new Id("a");
        Id id2 = new Id("b");
        FloatNum floatVal = new FloatNum("3.14");
        FloatNum floatVal2 = new FloatNum("0.025");
        Assigning floatAssign = new Assigning("a",id, floatVal);
        Assigning floatAssign2 = new Assigning("b",id2, floatVal2);
        expectedAST.addChild(floatAssign);
        expectedAST.addChild(floatAssign2);

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
    void AssignBoolTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignBool.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        Id id = new Id("a");
        Id id2 = new Id("b");
        Bool boolVal = new Bool("true");
        Bool boolVal2 = new Bool("false");
        Assigning boolAssign = new Assigning("a",id, boolVal);
        Assigning boolAssign2 = new Assigning("b",id2, boolVal2);
        expectedAST.addChild(boolAssign);
        expectedAST.addChild(boolAssign2);

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
    void AssignIntDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignIntDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        IntDcl intDcl = new IntDcl("a");
        IntDcl intDcl2 = new IntDcl("b");
        IntNum intVal = new IntNum("3");
        IntNum intVal2 = new IntNum("12");
        Assigning intAssign = new Assigning("a",intDcl, intVal);
        Assigning intAssign2 = new Assigning("b",intDcl2, intVal2);
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
    void AssignFloatDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignFloatDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        FloatDcl floatDcl = new FloatDcl("a");
        FloatDcl floatDcl2 = new FloatDcl("b");
        FloatNum floatVal = new FloatNum("8.32");
        FloatNum floatVal2 = new FloatNum("0.97");
        Assigning floatAssign = new Assigning("a",floatDcl, floatVal);
        Assigning floatAssign2 = new Assigning("b",floatDcl2, floatVal2);
        expectedAST.addChild(floatAssign);
        expectedAST.addChild(floatAssign2);

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
    void AssignBoolDclTest() throws FileNotFoundException {
        // [GIVEN] That we run the parser with a file
        Compiler compiler = readFileToParse("Test/TestCode/assignBoolDcl.txt");

        // [GIVEN] That we create the expected AST from the code from the file
        Prog expectedAST = new Prog();
        BoolDcl boolDcl = new BoolDcl("p");
        BoolDcl boolDcl2 = new BoolDcl("q");
        Bool boolVal = new Bool("true");
        Bool boolVal2 = new Bool("false");
        Assigning boolAssign = new Assigning("p",boolDcl, boolVal);
        Assigning boolAssign2 = new Assigning("q",boolDcl2, boolVal2);
        expectedAST.addChild(boolAssign);
        expectedAST.addChild(boolAssign2);

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