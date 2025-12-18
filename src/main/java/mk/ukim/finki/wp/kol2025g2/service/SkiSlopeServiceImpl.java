package mk.ukim.finki.wp.kol2025g2.service;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.kol2025g2.model.SkiResort;
import mk.ukim.finki.wp.kol2025g2.model.SkiSlope;
import mk.ukim.finki.wp.kol2025g2.model.SlopeDifficulty;
import mk.ukim.finki.wp.kol2025g2.repository.SkiResortRepository;
import mk.ukim.finki.wp.kol2025g2.repository.SkiSlopeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.kol2025g2.service.FieldFilterSpecification.*;

@AllArgsConstructor
@Service
public class SkiSlopeServiceImpl implements SkiSlopeService {
    private final SkiSlopeRepository skiSlopeRepository;
    private final SkiResortRepository skiResortRepository;
    @Override
    public List<SkiSlope> listAll() {
        return skiSlopeRepository.findAll();
    }

    @Override
    public SkiSlope findById(Long id) {
        return skiSlopeRepository.findById(id).get();
    }

    @Override
    public SkiSlope create(String name, Integer length, SlopeDifficulty difficulty, Long skiResort) {
        SkiResort skiResortO = skiResortRepository.findById(skiResort).get();
        SkiSlope skiSlope = new SkiSlope(name,length,difficulty,skiResortO);
        return skiSlopeRepository.save(skiSlope);
    }

    @Override
    public SkiSlope update(Long id, String name, Integer length, SlopeDifficulty difficulty, Long skiResort) {
        SkiSlope skiSlope = skiSlopeRepository.findById(id).get();
        skiSlope.setName(name);
        skiSlope.setLength(length);
        skiSlope.setDifficulty(difficulty);
        SkiResort skiResortO = skiResortRepository.findById(skiResort).get();
        skiSlope.setSkiResort(skiResortO);
        return skiSlopeRepository.save(skiSlope);
    }

    @Override
    public SkiSlope delete(Long id) {
        SkiSlope skiSlope = skiSlopeRepository.findById(id).get();
        skiSlopeRepository.delete(skiSlope);
        return skiSlope;
    }

    @Override
    public SkiSlope close(Long id) {
        SkiSlope skiSlope= skiSlopeRepository.findById(id).get();
        if (!skiSlope.isClosed()){
            skiSlope.setClosed(true);
        }
        return skiSlope;
    }

    @Override
    public Page<SkiSlope> findPage(String name, Integer length, SlopeDifficulty difficulty, Long skiResort, int pageNum, int pageSize) {
        Specification<SkiSlope> specification = Specification.allOf(
            filterContainsText(SkiSlope.class,"name",name),
                filterEqualsV(SkiSlope.class,"length",length),
                filterEqualsV(SkiSlope.class,"difficulty",difficulty),
                filterEqualsV(SkiSlope.class,"skiResort",skiResort)
        );
        return this.skiSlopeRepository.findAll(specification, PageRequest.of(pageNum,pageSize));
    }
}
