package com.sjs.jsvill.service.memo;


import com.sjs.jsvill.dto.MemoDTO;
import com.sjs.jsvill.entity.Memo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemoService {
    Memo register(MemoDTO memoDTO);
    List<Memo> getList(Long unitRowid);
}

