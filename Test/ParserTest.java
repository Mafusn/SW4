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

            switch (expected.getClass().getSimpleName()) {
                case "IntDcl" -> {
                    assertInstanceOf(IntDcl.class, actual);
                    assertEquals(((IntDcl) actual).getId(), ((IntDcl) expected).getId());
                }
                case "BoolDcl" -> {
                    assertInstanceOf(BoolDcl.class, actual);
                    assertEquals(((BoolDcl) actual).getId(), ((BoolDcl) expected).getId());
                }
                case "FloatDcl" -> {
                    assertInstanceOf(FloatDcl.class, actual);
                    assertEquals(((FloatDcl) actual).getId(), ((FloatDcl) expected).getId());
                }
                case "IntNum" -> {
                    assertInstanceOf(IntNum.class, actual);
                    assertEquals(((IntNum) actual).getValue(), ((IntNum) expected).getValue());
                }
                case "FloatNum" -> {
                    assertInstanceOf(FloatNum.class, actual);
                    assertEquals(((FloatNum) actual).getValue(), ((FloatNum) expected).getValue());
                }
                case "Bool" -> {
                    assertInstanceOf(Bool.class, actual);
                    assertEquals(((Bool) actual).getValue(), ((Bool) expected).getValue());
                }
                case "Id" -> {
                    assertInstanceOf(Id.class, actual);
                    assertEquals(((Id) actual).getName(), ((Id) expected).getName());
                }
                case "Assigning" -> assertInstanceOf(Assigning.class, actual);
                case "BinOperator" -> assertInstanceOf(BinOperator.class, actual);
                case "Block" -> assertInstanceOf(Block.class, actual);
                case "Computing" -> assertInstanceOf(Computing.class, actual);
                case "If" -> assertInstanceOf(If.class, actual);
                case "IfElse" -> assertInstanceOf(IfElse.class, actual);
                case "Not" -> assertInstanceOf(Not.class, actual);
                case "Print" -> assertInstanceOf(Print.class, actual);
            }

            String className = expected.getClass().getSimpleName();
            if (className.equals("Bool") || className.equals("BoolDcl") || className.equals("FloatNum")
                || className.equals("FloatDcl") || className.equals("IntNum") || className.equals("IntDcl")) {
                switch (expected.getType(null).getClass().getSimpleName()) {
                    case "BooleanType" -> assertInstanceOf(BooleanType.class, actual.getType(null));
                    case "FloatType" -> assertInstanceOf(FloatType.class, actual.getType(null));
                    case "IntType" -> assertInstanceOf(IntType.class, actual.getType(null));
                }
            }
        }
    }

}