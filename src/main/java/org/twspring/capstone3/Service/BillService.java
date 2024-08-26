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

    public List<Bill> getAllBills(){
        return billRepository.findAll();
    }

    public void addBill(Bill bill){
        billRepository.save(bill);
    }

    public void updateBill(Integer id, Bill updateBill){
        Bill bill = billRepository.findBillById(id)
                .orElseThrow(() -> new ApiException("BILL NOT FOUND"));

        bill.setAmount(updateBill.getAmount());
        billRepository.save(bill);
    }

    public void deleteBill(Integer id){
        Bill bill = billRepository.findBillById(id)
                .orElseThrow(() -> new ApiException("BILL NOT FOUND"));

        billRepository.delete(bill);
    }
}
