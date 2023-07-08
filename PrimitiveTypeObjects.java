public class PrimitiveTypeObjects {
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
