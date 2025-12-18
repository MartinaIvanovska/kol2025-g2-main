package mk.ukim.finki.wp.kol2025g2.service;

import mk.ukim.finki.wp.kol2025g2.model.SkiResort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkiResortServiceImpl implements SkiResortService {
    @Override
    public SkiResort findById(Long id) {
        return null;
    }

    @Override
    public List<SkiResort> listAll() {
        return List.of();
    }

    @Override
    public SkiResort create(String name, String location) {
        return null;
    }
}
