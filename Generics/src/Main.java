public class Main{
    public static void main(String[] arg){
        Measurable<Integer> w = new Weight<Integer>(65);
        Measurable<Integer> h = new Height(178);
        String[] names = {"Sam", "Kate", "Max"};
        w.<String>display(names);
        String[] heightsM = {"1,78", "1,97", "2,00"};
        Integer[] heightsSM = {178, 197, 200};
        h.<String, Integer>display(heightsM, heightsSM);

    }
}

interface Measurable<G>{
    G measureWithCoeff(G coeff);
    <G1> void display(G1[] args);
    <G2, G3> void display(G2[] inM, G3[] inC);
}
// при создании обобщенных методов не нужно указывать
// обобщения в объявлении интерфейса

// способ реализации обобщенного интерфейса
// с сохранением Generic
class Weight<G> implements Measurable<G> {
    G value;
    Weight(G value){
        this.value = value;
    }
    @Override
    public G measureWithCoeff(G coeff) {
        if(this.value != coeff) {
            return coeff;
        }
        return this.value;
    }
    // использование обобщенного метода
    public <G1> void display(G1[] args){
        System.out.println("Weights are:");
        for(G1 el : args){
            System.out.print(" " + el);
        }
        System.out.println(this.value);
    }

    @Override
    public <G2, G3> void display(G2[] inM, G3[] inC) {
        System.out.println("None");
    }

}

// способ реализации обобщенного интерфейса
// без сохранения Generic
class Height implements Measurable<Integer> {
    Integer value;
    Height(Integer value){
        this.value = value;
    }
    @Override
    public Integer measureWithCoeff(Integer coeff) {
        if(this.value.equals(coeff)) {
            return coeff;
        }
        return this.value;
    }
    //использование обобщенных методов в интерфейсах для описания разного набора
    public <G1> void display(G1[] args){
        System.out.println("None");
    }
    // несколько универсальных параметров
    public <G2, G3> void display(G2[] inM, G3[] inC){
        System.out.println("Heights in metre and santimetre:");
        for(int i = 0; i < inM.length; i++){
            System.out.println(inM[i] + " " + inC[i]);
        }
    }

}
