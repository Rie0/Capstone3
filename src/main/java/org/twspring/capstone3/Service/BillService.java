package org.twspring.capstone3.Service;

import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.Bill;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.twspring.capstone3.Repository.BillRepository;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;

    //It's only logical to view

    public List<Bill> getAllBills(){
        return billRepository.findAll();
    }
}
