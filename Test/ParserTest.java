import AST.Nodes.*;
import AST.Types.BooleanType;
import AST.Types.FloatType;
import AST.Types.IntType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
            assertAST(AST, expectedAST);
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
            assertAST(AST, expectedAST);
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
            assertAST(AST, expectedAST);
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
            assertAST(AST, expectedAST);
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
            Prog AST = (Prog) compiler.prog();
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
            Prog AST = (Prog) compiler.prog();
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
        IntNum intVal = new IntNum("5");
        Assigning intAssign = new Assigning("a",id, intVal);
        Id id2 = new Id("b");
        IntNum intVal2 = new IntNum("10");
        Assigning intAssign2 = new Assigning("b",id2, intVal2);
        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertAST(AST, expectedAST);
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
        IntNum intVal = new IntNum("5");
        Assigning intAssign = new Assigning("a",id, intVal);
        Id id2 = new Id("b");
        IntNum intVal2 = new IntNum("10");
        Assigning intAssign2 = new Assigning("b",id2, intVal2);
        expectedAST.addChild(intAssign);
        expectedAST.addChild(intAssign2);

        // [WHEN] We try to parse the code from the file
        try {
            Prog AST = (Prog) compiler.prog();
            // [THEN] Assert that the created AST is equal to the expected AST
            assertAST(AST, expectedAST);
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

    private void assertAST(Prog AST, Prog expectedAST) {
        ArrayList<Node> ASTChildren = AST.getChildren();
        ArrayList<Node> expectedASTChildren = expectedAST.getChildren();
        Assertions.assertEquals(ASTChildren.size(), expectedASTChildren.size());

        for (int i = 0; i < expectedASTChildren.size(); i++) {
            Node actual = ASTChildren.get(i);
            Node expected = expectedASTChildren.get(i);

            assertNodes(actual, expected);
        }
    }

    private void assertNodes(Node actualNode, Node expectedNode) {
        switch (expectedNode.getClass().getSimpleName()) {
            case "IntDcl" -> {
                assertInstanceOf(IntDcl.class, actualNode);
                assertEquals(((IntDcl) actualNode).getId(), ((IntDcl) expectedNode).getId());
            }
            case "BoolDcl" -> {
                assertInstanceOf(BoolDcl.class, actualNode);
                assertEquals(((BoolDcl) actualNode).getId(), ((BoolDcl) expectedNode).getId());
            }
            case "FloatDcl" -> {
                assertInstanceOf(FloatDcl.class, actualNode);
                assertEquals(((FloatDcl) actualNode).getId(), ((FloatDcl) expectedNode).getId());
            }
            case "IntNum" -> {
                assertInstanceOf(IntNum.class, actualNode);
                assertEquals(((IntNum) actualNode).getValue(), ((IntNum) expectedNode).getValue());
            }
            case "FloatNum" -> {
                assertInstanceOf(FloatNum.class, actualNode);
                assertEquals(((FloatNum) actualNode).getValue(), ((FloatNum) expectedNode).getValue());
            }
            case "Bool" -> {
                assertInstanceOf(Bool.class, actualNode);
                assertEquals(((Bool) actualNode).getValue(), ((Bool) expectedNode).getValue());
            }
            case "Id" -> {
                assertInstanceOf(Id.class, actualNode);
                assertEquals(((Id) actualNode).getName(), ((Id) expectedNode).getName());
            }
            case "Assigning" -> {
                assertInstanceOf(Assigning.class, actualNode);
                Assigning assigningActual = (Assigning) actualNode;
                Assigning assigningExpected = (Assigning) expectedNode;
                assertEquals(assigningActual.getVariable(), assigningExpected.getVariable());

                assertNodes(assigningActual.getDeclaration(), assigningExpected.getDeclaration());
                assertNodes(assigningActual.getExpression(), assigningExpected.getExpression());
            }
            case "BinOperator" -> assertInstanceOf(BinOperator.class, actualNode);
            case "Block" -> assertInstanceOf(Block.class, actualNode);
            case "Computing" -> assertInstanceOf(Computing.class, actualNode);
            case "If" -> assertInstanceOf(If.class, actualNode);
            case "IfElse" -> assertInstanceOf(IfElse.class, actualNode);
            case "Not" -> assertInstanceOf(Not.class, actualNode);
            case "Print" -> assertInstanceOf(Print.class, actualNode);
        }

        String className = expectedNode.getClass().getSimpleName();
        if (className.equals("Bool") || className.equals("BoolDcl") || className.equals("FloatNum")
                || className.equals("FloatDcl") || className.equals("IntNum") || className.equals("IntDcl")) {
            switch (expectedNode.getType(null).getClass().getSimpleName()) {
                case "BooleanType" -> assertInstanceOf(BooleanType.class, actualNode.getType(null));
                case "FloatType" -> assertInstanceOf(FloatType.class, actualNode.getType(null));
                case "IntType" -> assertInstanceOf(IntType.class, actualNode.getType(null));
            }
        }
    }
}