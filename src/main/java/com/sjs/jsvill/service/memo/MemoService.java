package com.sjs.jsvill.service.memo;


import com.sjs.jsvill.entity.Memo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemoService {
    Memo register(Memo memo);
    List<Memo> getList(Long unitRowid);
}

