package com.bps.productsurvey.model;

import java.util.ArrayList;
import java.util.List;

public class BarangFilter extends Barang implements QueryFilter{
    public BarangFilter(String nama) {
        super(nama);
    }

    private List<String> whereClauses = new ArrayList<>();
    private List<Object> whereParameters = new ArrayList<>();

    public void composeWhereQuery() {
        if (this.nama != null) {
            whereClauses.add("b.nama = ?");
            whereParameters.add(this.nama);
        }
    }

    public String getWhereClause() {
        if (whereClauses.size() == 0)
            return "";
        return " WHERE " + String.join(",",whereClauses);
    }

    public Object[] getWhereParameters(){
        if (whereParameters.size() == 0)
            return null;
        return whereParameters.toArray();
    }
}
