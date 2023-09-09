package com.sjs.jsvill.service.memo;

import com.sjs.jsvill.dto.MemoDTO;
import com.sjs.jsvill.entity.Memo;
import com.sjs.jsvill.repository.MemoRepository;
import com.sjs.jsvill.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;
    private final UnitRepository unitRepository;

    @SneakyThrows
    @Override
    public Memo register(MemoDTO memoDTO) {
        return memoRepository.save(Memo.dtoToEntity(memoDTO));
    }

    @Override
    public List<Memo> getListByUnit(Long unitRowid) {
        System.out.println("unitRowid : " + unitRowid);
        return memoRepository.findTop30ByUnitOrderByMemoRowidDesc(unitRepository.getById(unitRowid));
    }

    @Override
    public void remove(Long memoRowid) {
        memoRepository.delete(Memo.builder().memoRowid(memoRowid).build());
    }
}
