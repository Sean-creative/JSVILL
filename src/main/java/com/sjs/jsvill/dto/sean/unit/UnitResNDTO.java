//package com.sjs.jsvill.dto.sean.unit;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class UnitResNDTO {
//    private Long unitRowid; //수정, 삭제
//    private Long groupRowid; //건물 관리로 이동
//    private String detailAddr; //101호
//    private String memo; //비고
//
//    private String groupTitle;//건물이름
//    private String groupAddr;//건물주소
//
//    public class ContractDTO {
//        private Long contractRowid;
//        private Long unitRowid;
//        private Long ContractTypeRowid;
//        private String startdate; //view에서 name을 startDate로 하게되면 value값이 제대로 들어가지 않아서 이렇게 처리함
//        private String enddate;
//        private Long deposit; //보증금
//        private Long rentFee; //월세
//        private Long managementFees; //관리비
//        private Long paymentDay; //납부일
//        private Long dDay; //startDate-endDate
//
//        public class TenantDTO {
//            private Long tenantRowid;
//            private Long livingType;
//            private String title;
//            private String phone;
//            private Boolean isContractor;
//            public class CarDTO {
//                private Long carRowid;
//                private Long tenantRowid;
//                private String title;
//                private String number;
//            }
//            @Builder.Default
//            private List<CarDTO> carDTOList = new ArrayList<>();
//        }
//        public class OptionDTO {
//            private Long optionRowid;
//            private Long contractRowid;
//            @Builder.Default
//            private List<String> optionList = new ArrayList<>();
//        }
//
//        @Builder.Default
//        private List<TenantDTO> tenantDTOList = new ArrayList<>();
//        @Builder.Default
//        private OptionDTO optionDTO = new OptionDTO();
//    }
//
//    //계약리스트
//    @Builder.Default
//    private List<ContractDTO> contractDTOList = new ArrayList<>();
//}
