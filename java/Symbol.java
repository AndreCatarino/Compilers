public class Symbol
{
   public Symbol(String name, Type type) {
      assert name != null;
      assert type != null;

      this.name = name;
      this.type = type;
      this.valueDefined = false;
      this.iterable = false;
   }

   public Symbol(String name, Type type, Value value) {
      //assert name != null;
      //assert type != null;

      this.name = name;
      this.type = type;
      this.value = value;
      this.valueDefined = true;
   }

   public void setValue(Value value) {
      assert value != null;

      this.value = value;
      setValueDefined();
   }

   public String name(){
      return name;
   }

   public void setIterable() {
     this.iterable = true;
   }

   public boolean isIterable() {
     return this.iterable;
   }

   public void setVarName(String varName) {
      assert varName != null;

      this.varName = varName;
   }

   public String varName(){
      return varName;
   }

   public Type type(){
      return type;
   }

   public void setValueDefined(){
      valueDefined = true;
   }

   public boolean valueDefined(){
      return valueDefined;
   }

   public boolean isArray() {
      return isarray;
   }

   public void setArray(){
      isarray = true;
      len = 0;
   }


   public int getlen(){
      return len;
   }

   public void setlen(int l){
      len = l;
   }

   public Value value(){
      assert valueDefined();

      return value;
   }

   protected final String name;
   protected final Type type;
   protected Value value;
   protected boolean valueDefined;
   protected String varName;
   protected boolean iterable;
   private int len;
   private boolean isarray;
}

