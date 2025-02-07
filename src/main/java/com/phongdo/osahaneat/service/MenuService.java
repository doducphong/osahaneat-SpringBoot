package com.phongdo.osahaneat.service;

import org.springframework.web.multipart.MultipartFile;

public interface MenuService {
    boolean createMenu(
            MultipartFile file, String title, boolean is_freeship, String time_ship, double price, int cate_id);
}
