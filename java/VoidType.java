public class VoidType extends Type {
   public VoidType() {
      super("void", "void");
   }

   @Override
   public boolean isNumeric() {
      return false;
   }
}
