package com.test.pet.ApiTestCases;

import controller.PetsController;
import dataModel.Category;
import dataModel.Pet;
import dataModel.Status;
import dataModel.Tag;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import responseDataModal.PetPojoResponse;

import java.util.Collections;


public class PetTests extends BaseTest {
    private static final String PHOTO_URL = "https://www.istockphoto.com/photo/mother-and-calf-dolphin-swimming-by-gm1303043820-394618104";
    PetsController petsController;
    Pet pet = new Pet.Builder()
            .withId(RandomStringUtils.randomNumeric(5))
            .withName("Dolphins")
            .withPhotoUrls(Collections.singletonList(PHOTO_URL))
            .withStatus(Status.available)
            .withId("1")
            .withTags(Collections.singletonList(new Tag(1, "dolphins")))
            .inCategory(new Category(1, "dolphins")).build();

    @BeforeClass
    public void beforeClass() {
        petsController = new PetsController();
    }

    @Test(priority = 0)
    public void addNewPet() {
        Response response = petsController.addNewPet(pet, captor);
        PetPojoResponse petResponse = response.as(PetPojoResponse.class);
        Assert.assertEquals(petResponse.getId(), pet.getId());
        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

    }

    @Test(priority = 1)
    public void verifyNewPet() {
        PetPojoResponse petResponse = petsController.findPet(pet);
        Assert.assertEquals(petResponse.getId(), pet.getId());
    }

}
