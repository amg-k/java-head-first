public class PrimitiveTypeObjectTester {
    public static void main(String[] args) {
        PrimitiveTypeObjects p = new PrimitiveTypeObjects();
        System.out.println(p.getI());
        System.out.println(p.getIobj());
        p.doIt();
        }
    }

class PrimitiveTypeObjects {
    private Integer iObj;
    private int i;

    public void doIt() {
        this.iObj = 4;
        this.i = this.iObj;
        System.out.println(this.i);
        System.out.println(this.iObj);
    }

    public int getI() {
        return i;
    }

    public Integer getIobj() {
        return iObj;
    }    
}    