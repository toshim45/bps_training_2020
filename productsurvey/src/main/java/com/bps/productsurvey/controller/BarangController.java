package com.bps.productsurvey.controller;

import com.bps.productsurvey.model.Barang;
import com.bps.productsurvey.model.BarangFilter;
import com.bps.productsurvey.model.PageFilter;
import com.bps.productsurvey.service.BarangService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BarangController {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private BarangService service;

    private static final Logger logger = LoggerFactory.getLogger(BarangController.class);

    @GetMapping("/barangs")
    public List<Barang> list(@RequestParam(required = false) String nama){
        return service.all(new BarangFilter(nama));
    }

    @GetMapping("/barangs/search")
    public Page<Barang> search(@RequestParam(required = false) String nama, PageFilter pageFilter){
        if (pageFilter == null) {
            pageFilter = new PageFilter();
        }
        return service.search(new BarangFilter(nama), pageFilter);
    }

    @PostMapping("/barangs")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody Barang barang){
        service.create(barang);
    }

    @DeleteMapping("/barangs/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name="id") int id) {
        service.delete(id);
    }


}
