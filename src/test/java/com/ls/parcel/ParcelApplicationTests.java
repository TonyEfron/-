package com.ls.parcel;

import com.ls.parcel.mapper.CompanyMapper;
import com.ls.parcel.pojo.Company;
import com.ls.parcel.service.DumpSQLService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ParcelApplicationTests {
    @Autowired
    private CompanyMapper companyMapper;

    @Test
    void contextLoads() {
//        List<Company> company=companyMapper.selectAllCompany();
//        System.out.println(company);
//        DumpSQLService dumpSQLService;
//        dumpSQLService.dump();
    }

}
