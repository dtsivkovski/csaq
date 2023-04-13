package com.nighthawk.spring_portfolio.mvc.FRQData;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/FRQdata")
public class FRQDataApiController {
    //get all FRQData objects
@GetMapping("/all")
public FRQData[] getAllFRQData() {
    return FRQData.frqs();
}

//get FRQData object by description
@GetMapping("/description/{desc}")
public FRQData getFRQDataByDescription(@PathVariable String desc) {
    FRQData.setOrder(FRQData.KeyType.description);
    FRQData[] frqs = FRQData.frqs();
    for (FRQData frq : frqs) {
        if (frq.toString().equals(desc)) {
            return frq;
        }
    }
    return null;
}

//get FRQData object by input
@GetMapping("/input/{input}")
public FRQData getFRQDataByInput(@PathVariable String input) {
    FRQData.setOrder(FRQData.KeyType.input);
    FRQData[] frqs = FRQData.frqs();
    for (FRQData frq : frqs) {
        if (frq.toString().equals("Sample Input: " + input)) {
            return frq;
        }
    }
    return null;
}

//get FRQData object by output
@GetMapping("/output/{output}")
public FRQData getFRQDataByOutput(@PathVariable String output) {
    FRQData.setOrder(FRQData.KeyType.output);
    FRQData[] frqs = FRQData.frqs();
    for (FRQData frq : frqs) {
        if (frq.toString().equals("Sample Output: " + output)) {
            return frq;
        }
    }
    return null;
}
}
