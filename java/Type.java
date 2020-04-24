public abstract class Type
{
   protected Type(String name, String c_name) {
      assert name != null;
      this.name = name;
      this.c_name = c_name;
   }

   public String name() {
      return name;
   }

   public boolean conformsTo(Type other) {
      return name.equals(other.name());
   }

   public boolean isNumeric() {
      return false;
   }

   @Override
   public String toString()
   {
      return name;
   }

   public String toStringC()
   {
      return c_name;
   }

   protected final String name;
   protected final String c_name;

}

