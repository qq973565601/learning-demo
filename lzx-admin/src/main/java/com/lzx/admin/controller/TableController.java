package com.lzx.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lzx
 * @since 2022/10/13
 */
@Controller
public class TableController {

    @GetMapping("/basic_table")
    public String basicTable() {
        return "table/basic_table";
    }

    @GetMapping("/dynamic_table")
    public String dynamicTable() {
        return "table/dynamic_table";
    }

    @GetMapping("/responsive_table")
    public String responsiveTable() {
        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public String editableTable() {
        return "table/editable_table";
    }

    @GetMapping("/pricing_table")
    public String pricingTable() {
        return "table/pricing_table";
    }
}
