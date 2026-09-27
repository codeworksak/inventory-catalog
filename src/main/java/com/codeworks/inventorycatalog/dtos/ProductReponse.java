package com.codeworks.inventorycatalog.dtos;

import java.util.UUID;

public record ProductReponse(UUID Id,String Name,String Brand, String Category,Double Price){

}