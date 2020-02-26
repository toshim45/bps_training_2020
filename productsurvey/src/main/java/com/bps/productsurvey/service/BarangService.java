package com.bps.productsurvey.service;

import com.bps.productsurvey.model.Barang;
import com.bps.productsurvey.model.BarangFilter;
import com.bps.productsurvey.model.PageFilter;
import com.bps.productsurvey.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarangService {
    @Autowired
    BarangRepository repository;

    public List<Barang> all(BarangFilter filter){
        return repository.findAll(filter);
    }

    public void create(Barang barang){
        repository.insert(barang);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Page<Barang> search(BarangFilter barangFilter, PageFilter pageFilter) {
        return repository.search(barangFilter, pageFilter);
    }
}
