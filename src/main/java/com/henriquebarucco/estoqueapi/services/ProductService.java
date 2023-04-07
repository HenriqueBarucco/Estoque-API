package com.henriquebarucco.estoqueapi.services;

import com.henriquebarucco.estoqueapi.controllers.product.dto.ProductDto;
import com.henriquebarucco.estoqueapi.entities.Product;
import com.henriquebarucco.estoqueapi.repositories.ProductRepository;
import com.henriquebarucco.estoqueapi.services.exceptions.ProductAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<Product> findAll() {
        return productRepository.findAll();
    }
/*
    public Planning findById(Long id) {
        Optional<Planning> obj = planningRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(PlanningDto planning) {
        try {
            Planning deletedPlanning = planningRepository.findFirstByCicloAndTurmaAndDate(planning.getCiclo(), planning.getTurma(), planning.getDate());
            optionsService.removePlace(deletedPlanning);
            planningRepository.deleteByCicloAndTurmaAndDate(planning.getCiclo(), planning.getTurma(), planning.getDate());
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(planning.getDate());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Planning find(PlanningDto planningDto) {
        Planning planning = planningRepository.findFirstByCicloAndTurmaAndDate(planningDto.getCiclo(), planningDto.getTurma(), planningDto.getDate());
        if (planning == null) {
            throw new ResourceNotFoundException(planningDto.getDate());
        }
        return planning;
    }*/

    public Product insert(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);

        if (productRepository.findFirstByName(product.getName()) != null) {
            throw new ProductAlreadyExistsException();
        }

        Product addedProduct = productRepository.save(product);
        return addedProduct;
    }

    /*public void update(PlanningTimetableDto planning) {
        Planning obj = modelMapper.map(planning, Planning.class);
        try {
            Planning entity = planningRepository.findFirstByCicloAndTurmaAndDate(obj.getCiclo(), obj.getTurma(), obj.getDate());
            updateData(entity, obj);
            planningRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(obj.getDate());
        }
    }

    private void updateData(Planning entity, Planning obj) {
        HistoryPlanningDao historyPlanningDao = entity.getHistoryPlanningDao();

        var lista = historyPlanningDao.getEditedBy();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = LocalDateTime.now().format(formatter);

        String editedBy = SecurityContextHolder.getContext().getAuthentication().getName();
        HistoryInfoDao historyInfoDao = new HistoryInfoDao(editedBy, currentDateTime);

        lista.add(0, historyInfoDao);

        entity.setHistoryPlanningDao(historyPlanningDao);
        entity.setPlanning(obj.getPlanning());
    }*/
}
