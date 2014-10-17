
/**
 * All runtime values must inherit from Value.
 */
interface Value {
    public int toInt() ;
    public boolean toBoolean() ;
}


/**
 * VoidValue.VOID is returned when the result can be ignored.
 */
class VoidValue implements Value {
    private VoidValue() {
    }

    public static final VoidValue VOID = new VoidValue() ;

    /**
     * Converts a value into the void value.
     */
    public static final VoidValue Void(Value v) {
        return VOID ;
    }

    public int toInt() {
        throw new RuntimeException("Cannot treat void as int!") ;
    }

    public boolean toBoolean() {
        return true ;
    }
}


/**
 * Integer values.
 */
class IntValue implements Value {
    private int n  ;

    public IntValue(int n) {
        this.n = n ;
    }

    public int toInt() {
        return this.n ;
    }
    
    public boolean toBoolean() {
        return true ;
    }

    public String toString() {
        return String.valueOf(n) ;
    }
}


/**
 * Boolean values.
 */
class BooleanValue implements Value {
    private boolean b ;

    private BooleanValue(boolean b) {
        this.b = b ;
    }

    public int toInt() {
        throw new RuntimeException("Cannot treat boolean as int!") ;
    }

    public boolean toBoolean() {
        return b ;
    }

    public static final BooleanValue TRUE  = new BooleanValue(true) ;
    public static final BooleanValue FALSE = new BooleanValue(false) ;

    public static final BooleanValue fromBoolean(boolean p) {
        return p ? TRUE : FALSE ;
    }
}


/**
 * Procedural values.
 */
interface ProcValue extends Value {
}


/**
 * Procedural values with standard procedures pre-defined.
 */
abstract class NullProcValue implements ProcValue {
    public int toInt() {
        throw new RuntimeException("Cannot treat procedure to int!") ;
    } 

    public boolean toBoolean() {
        return true ;
    } 
}

interface ProcValue0 extends ProcValue {
    abstract public Value apply() ;
}

interface ProcValue1 extends ProcValue {
    abstract public Value apply(Value arg1) ;
}

interface ProcValue2 extends ProcValue {
    abstract public Value apply(Value arg1, Value arg2) ;
}

interface ProcValue3 extends ProcValue {
    abstract public Value apply(Value arg1, Value arg2, Value arg3) ;
}


abstract class NullProcValue0 extends NullProcValue implements ProcValue0 {}
abstract class NullProcValue1 extends NullProcValue implements ProcValue1 {}
abstract class NullProcValue2 extends NullProcValue implements ProcValue2 {}
abstract class NullProcValue3 extends NullProcValue implements ProcValue3 {}


/**
 * Primitives values are multi-arity procedures.
 */
interface Primitive extends ProcValue, ProcValue0, ProcValue1, ProcValue2, ProcValue3 {
    public Value apply() ;
    public Value apply(Value arg1) ;    
    public Value apply(Value arg1, Value arg2) ;
    public Value apply(Value arg1, Value arg2, Value arg3) ;
}

abstract class NullPrimitive implements Primitive {
    public Value apply() {
        throw new RuntimeException("0 arguments not supported") ;
    }
    public Value apply(Value arg1) {
        throw new RuntimeException("1 argument not supported") ;
    }  
    public Value apply(Value arg1, Value arg2) {
        throw new RuntimeException("2 arguments not supported") ;
    }
    public Value apply(Value arg1, Value arg2, Value arg3) {
        throw new RuntimeException("3 arguments not supported") ;
    }

    public int toInt() {
        throw new RuntimeException("Cannot treat primitive as int!") ;
    }

    public boolean toBoolean() {
        return true ;
    }
}


/**
 * Top-level bindings for the run-time environment.
 */
class RuntimeEnvironment {

    public static final Primitive display = new NullPrimitive () {
            public Value apply(Value arg1) {
                System.out.println(arg1) ;
                return VoidValue.VOID ;
            }
        } ;

    public static final Primitive sum = new NullPrimitive () {
            public Value apply(Value arg1, Value arg2) {
                return new IntValue(arg1.toInt() + arg2.toInt()) ;
            }
        } ;

    public static final Primitive product = new NullPrimitive () {
            public Value apply(Value arg1, Value arg2) {
                return new IntValue(arg1.toInt() * arg2.toInt()) ;
            }
        } ;

    public static final Primitive difference = new NullPrimitive () {
            public Value apply(Value arg1, Value arg2) {
                return new IntValue(arg1.toInt() - arg2.toInt()) ;
            }
        } ;

    public static final Primitive numEqual = new NullPrimitive () {
            public Value apply(Value arg1, Value arg2) {
                return BooleanValue.fromBoolean(arg1.toInt() == arg2.toInt()) ;
            }
        } ;
}



/**
 * Mutable variables become ValueCells.
 */
class ValueCell {
    public Value value ;

    public ValueCell(Value initialValue) {
        this.value = initialValue ;
    }
}
