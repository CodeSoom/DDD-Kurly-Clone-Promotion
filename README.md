## 코드숨 마켓컬리 Domain Driven Design 클론 코딩 스터디 프로모션팀
마켓컬리의 프로모션 도메인을 담당한다.

### [API Document](https://kurly-clone-promotion.codesoom.com/swagger-ui/)
### [Wiki](https://github.com/CodeSoom/DDD-Kurly-Clone-Promotion/wiki)

## 프로젝트 구조
```
├── main
    ├── java
          └── com
              └── kurly
                  ├── PromotionApplication.java
                  ├── common
                  │   ├── config
                  │   │   ├── JpaConfig.java
                  │   │   └── SwaggerConfig.java
                  │   └── model
                  │       └── BaseEntity.java
                  ├── coupon
                  │   ├── application
                  │   │   ├── CouponService.java
                  │   │   └── factory
                  │   │       ├── FactoryPolicies.java
                  │   │       ├── FixedAmountPolicyFactory.java
                  │   │       ├── FlatRatePolicyFactory.java
                  │   │       └── PolicyFactory.java
                  │   ├── domain
                  │   │   ├── Amount.java
                  │   │   ├── Count.java
                  │   │   ├── CouponPolicies.java
                  │   │   ├── CouponPolicy.java
                  │   │   ├── FixedAmount.java
                  │   │   ├── FlatRate.java
                  │   │   ├── Keyword.java
                  │   │   ├── MinimumRedeemPrice.java
                  │   │   ├── Name.java
                  │   │   ├── Period.java
                  │   │   ├── PolicyType.java
                  │   │   └── Rate.java
                  │   ├── dto
                  │   │   └── CouponPolicyPublishData.java
                  │   ├── infra
                  │   │   └── CouponPolicyRepository.java
                  │   └── ui
                  │       └── CouponController.java
                  └── promotion
                      ├── application
                      │   └── DiscountService.java
                      ├── controller
                      │   └── DiscountController.java
                      ├── domain
                      │   └── Discount.java
                      ├── dto
                      │   └── DiscountRegistrationData.java
                      ├── infra
                      │   └── DiscountRepository.java
                      └── ui
                          └── HealthCheckController.java
```
## 설치 방법
## 실행 방법
## 사용 및 기술환경
Spring Boot, H2 Database
