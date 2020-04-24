public class StringType extends Type {
   public StringType() {
      super("string", "char[]");
   }

   @Override
   public boolean isNumeric() {
      return false;
   }
}
