# spring-product-integration-service
spring-product-integration-service


### Sequence Diagram

```mermaid
sequenceDiagram
    actor User
    participant IntegrationService
    participant Product
    participant ProductOrder
    participant ProductInventory
    User->>+IntegrationService: Create Product Order
    IntegrationService->>+Product: Find Product
    Product-->>-IntegrationService: [Return 200] Product Found
    IntegrationService->>+ProductOrder: Create Product Order
    ProductOrder-->>-IntegrationService: [Return 201] Product Order Created
    IntegrationService-->>-User: [Return 201] Created

%% UPDATE PRODUCT ORDER
    User->>+IntegrationService: [PUT] Update Product Order
    IntegrationService->>+ProductOrder: [PUT] Update Product Order
    ProductOrder-->>-IntegrationService: [Return 202] Accepted
    IntegrationService-->>-User: [Return 202] Accepted

%%
    alt Approve 
        User->>+IntegrationService: [PATCH] Approve Product Order
        IntegrationService->>+ProductOrder: [PATCH] Update Order Status for "Approved"
    else Reject
        User->>+IntegrationService: [PATCH] Reject Product Order
        IntegrationService->>+ProductOrder: [PATCH] Update Order Status for "Reject"
    end
    ProductOrder-->>-IntegrationService: [Return 202] Accepted
    IntegrationService-->>-User: [Return 202] Accepted
    
    User->>+IntegrationService: [PATCH] Complete Product Order
    IntegrationService->>+ProductOrder: [PATCH] Update Order Status for "Completed"
    ProductOrder-->>-IntegrationService: [Return 202] Accepted
    IntegrationService->>+ProductInventory: Create Product Inventory
    ProductInventory-->>-IntegrationService: [Return 201] Created
    IntegrationService-->>-User: [Return 202] 
```
### Others
[Sequence Ful](https://mermaid.live/edit#pako:eNrtWF1v2jAU_SuWpT1MhYqE0oY8VGJAN6RuRAQmbaOaouSOZg125jhsXdX_vpsvGpfQUpaXbvVDRBzfc499uCd2bqjLPaAmjeBHDMyFge8shLOcM4LNcSUXxI7DMPBBlPt6YSj4Su2bRcl91hM6QvquHzpMkhGTgJjS58wGsfJd2BxjCe7Frtz6YCy8Iln5aUbDCaqSflV5V4CO2AoYMr_ORiT8m6enB5t8TfLFGtvTC9IX4Ego4kmJ1WZQApUPxPi3Qww_85mnTjW_aeLgZmXeCchYMKK3WhfrtGc8Zt4OaVN6O3AvD9-BiXahguTI2xklkMni3gNRwrLrq1fEXDcyswa96ZCMJ4PhpNSftl0km-GsZ6H3HBV7mPoTBdPvC5YhP00wBOm5LoRpXJVa9vDDgEzHpGdZk_HH3vk-ivWm_XfriWdcJScWMM9ni2cgm8Lflo6Mo30Vy6Ifl-r1QWGCKAEuVLJgqi3WJXAmbF6PleLmb4OgMOaCd9KKN8ZTxVeRIIiATOA7uLIOcBUJdhL7P_yPIVaxwntZgj2zrPPRfiZeLZwN8kWqajso7XzuHEHdC9XlCMoL-s2ntc6PmYN7xfjPALxFuYYLitkcKpZjmOzWKgxiAy01CVwGXOWFgCj6-xwjRlSw3Idc8Fd1zEFFyvmvOMZ4dZAvI6XgluPXAJygrIVNcQcQ4DREHazXUDnOP2nRdTr0XdFvreCqGkZ77p-P7Qe32ftq2A94lAj4YtVbNKvCGjKPNugSxBLLC0_mNwnCnMpLWMKcmvjTc8TVnM7ZLY5zYsnta-ZSU4oYGjROs-aneGp-c7AsGxRPvdS8ob-o2dQ0o32o6brePjnRDO34SGvQa-xv6ydHh92W0e5oXe24ZXQ7tw36m3MEMQ7bmtY1NOOo0221O8d6Cvg5fZZlBc_Hc_T77FNC-kWhQQWPF5c5g9s_C41zhQ)

[Order State Ful](https://mermaid.live/edit#pako:eNqVVl1uGzcQvspggQBqIQtZyT-yEBgwGgfIQxvB6VOrIhgvKYn2Lrkld9U4hg8T9KHoAXoCXawz5FKiLDluZcCQZoYz33zf7HAfssIImU2ymXYNNvKtwoXF6mg1nGmgjzeCmn-qpRZKL-DNm2JpVCEvLp4ECIvzhtxzY-_YGdy_fv8bHB1dwFv2BpP_6o3xVLBvcrBrGsod8PyAupClFLFCFxkT1k8O1on7sq6tWfHRA85reSuLZps3NBaPwEOwpj19lLrZWvlXqFLcafMHIVzESvxJrT7svf40tWZhpXPbqMTYgSqkWqV5oqXLsWItRJogWAKJqBIX_woMlsZt-3zcbTjSkDb8gXiCSaphSgN795KFGt5PcZ2tU25j5L9Xr-AaXdHqpYGer_DdxFs9aWCskBUICYWpaouw_hsKq1BgHyp0gEoLBL3-04B0zforzJXGUn2hADAtSL3i2AH8RF7JIShMH2oaeXDSghSq4VAJVq6U41Bf2__jwSJNJfS6EfsmsLlR4NqbSjaKEtZIJuTRwfVfhG4PLYcHf0BqiXaPZQBTRsdQLeCiRStQCwNt5eMqVG6b-B_pEsCXMV8vTu2LiGPbxMAGTW0sNEYwXseNNNKBpqlzbv2VqHcRoW64EU9joBmQ-7IcK4xNgF3H5qAXx-v_IEu4OaBjbWVBkZbP423rGC4RVRhrZaAIULOx03wrkqZ-K1Y4gXrVtdLjx_lFkAcbD-ITm0yZL2BSkQruhSRK9kFS50OaiDqTN5LGmGrqubIV9YVPgQzgquQefd4KjINGUqQLp4TqOJCuJp7mqiDm0qG5quCSptLDhN57DXH_PAcqPGexPR7NPUh9FoVZYC8hoWDRNsbrQhKsCJNxgSZmMKVnirZQWHpZ_K67YaV6ZG7IXN5v9l-qDS2gOKognq0XpipkHBwQNDydoT28kZZUwob2ze-t5OeB0zlQjdSOkt4S1V2yHTq3kA8A_TlmOQxQ4IEhs2kh0nQl7QEV32HTWj-38QJ4Vj4mnMdo7o8EFb7BGR_0PcIVPXXxFEUkQxAXXo2LvXnfEXSKC5OKyVdSAnTaKQgrLAnqQT6Ur5LuU8vrgPYjOCzFbulQTe2SsUHpk5ayCdt4Ts-PeXaFvZPF0vMbLrYXN0OXOuyXzdj1O_78WmMcg52ZYDLpm5UlFspo9Io7v2Y399qO7OFGDbj81_K_QNsckuEu2hMzuTmzflbROiEO6R3RvxLMsmYpKznLJvRVoL2b0bvjI8Uhjc_He11kk8a2sp-1tdi-UEZjjTqbPGSfs8lRno9Hg3w4HI7OzvJxfnqc97N7so-GZ8eD89fj0Ul-np--Hp-fPPazL8ZQjvFglOfn42F-cjw-PiW3T_iL982xdFSAr3Rjfwwvtf7dtp9Z0y6WXcTjvyA5qtc)