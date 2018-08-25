import java.util.ArrayList;

public class ProdukResult {
    private String status;
    private ArrayList<Produk> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Produk> getData() {
        return data;
    }

    public void setData(ArrayList<Produk> data) {
        this.data = data;
    }
}
