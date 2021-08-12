package tests;

import com.pets.helpers.PetServiceHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertNotNull;

public class SwaggerTest {
       private PetServiceHelper petServiceHelper;

    @BeforeClass
    public void init(){
        petServiceHelper = new PetServiceHelper();
    }

    @Test(priority = 1)
    @Owner("Vishakha")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifying Pet was added successfully")
    public void AddingPet() {
        String id = petServiceHelper.AddNewPet().jsonPath().getString("id");
        System.out.println(id);
        assertNotNull(id, "Pet id is not null");
    }

    @Test(priority = 2)
    @Owner("Vishakha")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifying Pet was removed successfully")
    public void DeletingPet(){
         petServiceHelper.DeletePet(19);
    }

    @Test(priority = 3)
    @Owner("Vishakha")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifying order was placed successfully ")
    public void BuyPet() {
        String id = petServiceHelper.PlaceOrder().jsonPath().getString("id");
        System.out.println(id);
        assertNotNull(id, "Pet id is not null");
    }

    @Test(priority = 4)
    @Owner("Vishakha")
    @Severity(SeverityLevel.MINOR)
    @Description("Verifying details for purchased order ")
    public void Order(){
        petServiceHelper.OrderNotFound(3);
    }



}
