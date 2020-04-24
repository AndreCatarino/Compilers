public class RealType extends Type {
   public RealType() {
      super("real", "double");
   }
   @Override
   public boolean conformsTo(Type other) {
      if (other.name().equals("real") || other.name().equals("integer"))
         return true;
      return false;
   }
   
   @Override
   public boolean isNumeric() {
      return true;
   }
}

