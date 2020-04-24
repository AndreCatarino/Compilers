import static java.lang.System.*;
import java.util.*;

// import sun.jvm.hotspot.debugger.cdbg.Sym;

public class SymbolTable{
    private Map<String,Symbol> defaultTable = new HashMap<>();
    private Map<String,Symbol> table = defaultTable;

    public SymbolTable () {
      this.table = new HashMap<>();
    }

    public void setNewSymbolTable(){
        this.defaultTable = table;
        table = new HashMap<>(defaultTable);
    } 

    public void setDefaultSymbolTable(){
        table = defaultTable;
    }

    public boolean exists(String name){
        return table.containsKey(name);
    }

    public void put(String name, Symbol s){
        table.put(name,s);
    }

    public void put(String name, String type, String value){
        //assert name!=null;
        //assert value!=null;
        //assert exists(name);
        
        Type t = null;
        Value v = null;

        if (type.equals("real")){
            t = new RealType();
            v = new RealValue(Double.parseDouble(value));
        }
        else if (type.equals("integer")){
            t = new IntegerType();
            v = new IntegerValue(Integer.parseInt(value));
        }
        else if (type.equals("string")){
            t = new StringType();
            v = new StringValue(value);
        }

        Symbol s = new Symbol(name, t, v);

        table.put(name,s);
    }

    public void put(String name, String type){
        //assert name!=null;
        //assert exists(name);
    
        Type t = null;

        if (type.equals("real")){
            t = new RealType();
        }
        else if (type.equals("integer")){
            t = new IntegerType();
        }
        else if (type.equals("string")){
            t = new StringType();
        }

        Symbol s = new Symbol(name, t);
        table.put(name,s);
    }

    public void put(String name, Type type){
        //assert name!=null;
        //assert exists(name);
    
        Symbol s = new Symbol(name, type);
        table.put(name,s);
    }


    public Symbol get(String name){
        return table.get(name);
    }

    public String getType(String name) {
        Symbol s = get(name);
        return s.type().toString();
    }

    public String getTypeC(String name) {
        Symbol s = get(name);
        return s.type().toStringC();
    }

    public int size() {
      return this.table.size();
    }

    @Override public String toString() {
      return this.table.toString();
    }

    public boolean isIterable(String name) {
      return this.table.get(name).isIterable();
    }

    public void setIterable(String name) {
      this.table.get(name).setIterable();
    }
}
