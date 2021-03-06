package org.manifold.compiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import org.manifold.compiler.front.EnumIdentifierNotDefined;
import org.manifold.compiler.front.EnumTypeValue;
import org.manifold.compiler.front.EnumValue;
import org.manifold.compiler.front.TypeMismatchException;

public class TestEnumValue {

  private final HashMap<String, Value> enumMap = new HashMap<>();

  @Before
  public void setup() {
    enumMap.clear();
    enumMap.put("foo", BooleanValue.getInstance(true));
    enumMap.put("bar", BooleanValue.getInstance(false));
  }

  @Test
  public void testGetters()
      throws TypeMismatchException, EnumIdentifierNotDefined {
    EnumTypeValue type = new EnumTypeValue(
        BooleanTypeValue.getInstance(),
        enumMap
    );
    EnumValue foo1 = new EnumValue(type, "foo");
    assertEquals("foo", foo1.getIdentifier());
    assertEquals(type, foo1.getType());
  }

  @Test
  public void testValueInheritedMethods() throws Exception {
    EnumTypeValue type = new EnumTypeValue(
        BooleanTypeValue.getInstance(),
        enumMap
    );
    EnumValue foo1 = new EnumValue(type, "foo");
    foo1.verify();
    assertEquals(
      foo1.getValue().isElaborationtimeKnowable(),
      foo1.isElaborationtimeKnowable()
    );
    assertEquals(foo1.getValue().isRuntimeKnowable(), foo1.isRuntimeKnowable());
  }

  @Test
  public void testEnumEquality()
      throws TypeMismatchException, EnumIdentifierNotDefined {
    EnumTypeValue type = new EnumTypeValue(
        BooleanTypeValue.getInstance(),
        enumMap
    );
    EnumValue foo1 = new EnumValue(type, "foo");
    EnumValue foo2 = new EnumValue(type, "foo");

    assertEquals(foo1, foo2);
    assertEquals(foo1, BooleanValue.getInstance(true));
    assertNotEquals(foo1, BooleanValue.getInstance(false));
  }
}
