package br.com.booking.corebookingapi.resource;
/*
import br.com.booking.corebookingapi.enums.Status;
import br.com.booking.corebookingapi.exception.ApiExceptionHandler;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductResourceTest {

    @Mock
    ProductService productService;
    @Mock
    MessageSource messageSource;
    @InjectMocks
    private ProductResource productResource;
    @InjectMocks
    private ApiExceptionHandler apiExceptionHandler;

    private final String baseUrl = "/api/v1/products";

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.standaloneSetup(productResource, apiExceptionHandler);
    }

    @Test
    void givenMalFormedURL_whenCreateProduct_thenReturnNotFound() {

        String malFormedURL = "api/v1/prod";

        when(productService.createProduct(any(CreateUpdateProductDTO.class))).thenReturn(productDTODefault());
        when(messageSource.getMessage(anyString(),isNull(),isNull())).thenReturn("Mensagem");

        given().body(parameterBodyDefault()).contentType(ContentType.JSON).when().post(malFormedURL).then().statusCode(404);
    }

    /*@Test
    void givenNoToken_whenCreateProduct_thenReturnUnauthorized() {

        when(productService.createProduct(any(CreateUpdateProductDTO.class))).thenReturn(productDTODefault());

        given().body(parameterBodyDefault()).contentType(ContentType.JSON).when().post(baseUrl).then().statusCode(401);
    }*/

    /*@Test
    void givenInvalidCredentials_whenCreateProduct_thenReturnForbidden() {

        when(productService.createProduct(any(CreateUpdateProductDTO.class))).thenReturn(productDTODefault());

        given().body(parameterBodyDefault()).contentType(ContentType.JSON).when().post(baseUrl).then().statusCode(403);
    }*/
/*
    @Test
    void givenInvalidMethod_whenCreateProduct_thenReturnMethodNotAllowed() {

        when(productService.createProduct(any(CreateUpdateProductDTO.class))).thenReturn(productDTODefault());
        when(messageSource.getMessage(anyString(),isNull(),isNull())).thenReturn("Mensagem");

        given().body(parameterBodyDefault()).contentType(ContentType.JSON).when().delete(baseUrl).then().statusCode(405);
    }

    @Test
    void givenHeaderParameters_whenCreateProduct_thenReturnUnsupportedMediaType() {

        when(productService.createProduct(any(CreateUpdateProductDTO.class))).thenReturn(productDTODefault());
        when(messageSource.getMessage(anyString(),isNull(),isNull())).thenReturn("Mensagem");

        given().when().post(baseUrl).then().statusCode(415);
    }

    @Test
    void givenParametersWithoutName_whenCreateProduct_thenReturnBadRequest() {

        when(productService.createProduct(any(CreateUpdateProductDTO.class))).thenReturn(productDTODefault());
        when(messageSource.getMessage(anyString(),isNull(),isNull())).thenReturn("Mensagem");

        given().body(invalidParameterBodyDefault()).contentType(ContentType.JSON).when().post(baseUrl).then().statusCode(400);
    }
    @Test
    void givenValidParameters_whenCreateProduct_thenReturnOK() {

        when(productService.createProduct(any(CreateUpdateProductDTO.class))).thenReturn(productDTODefault());
        when(messageSource.getMessage(anyString(),isNull(),isNull())).thenReturn("Mensagem");

        given().body(parameterBodyDefault()).contentType(ContentType.JSON).when().post(baseUrl).then().statusCode(200);
    }

    @Test
    void givenValidParameters_whenUpdateProduct_thenReturnOK() {

        String updateURL = baseUrl + "/1";

        when(productService.updateProduct(anyLong(),any(CreateUpdateProductDTO.class))).thenReturn(productDTODefault());
        when(messageSource.getMessage(anyString(),isNull(),isNull())).thenReturn("Mensagem");

        given().body(parameterBodyDefault()).contentType(ContentType.JSON).when().put(updateURL).then().statusCode(200);
    }

    public static CreateUpdateProductDTO parameterBodyDefault() {

        return createUpdateProductDTODefault();
    }

    public static CreateUpdateProductDTO invalidParameterBodyDefault() {

        return invalidCreateUpdateProductDTODefault();
    }
    public static CreateUpdateProductDTO createUpdateProductDTODefault() {

        return CreateUpdateProductDTO.builder()
                .name("Teste")
                .description("Descrição")
                .barCode("9780201379624")
                .unitMeasurement(UnitMeasurement.UNIDADE)
                .productType(ProductType.VESTUARIO).build();

    }

    public static CreateUpdateProductDTO invalidCreateUpdateProductDTODefault() {

        return CreateUpdateProductDTO.builder()
                .description("Descrição")
                .barCode("9780201379624")
                .unitMeasurement(UnitMeasurement.UNIDADE)
                .productType(ProductType.VESTUARIO).build();

    }

    public static ProductDTO productDTODefault() {

        return ProductDTO.builder()
                .id(1L)
                .name("Teste")
                .description("Descrição")
                .barCode("9780201379624")
                .unitMeasurement(UnitMeasurement.UNIDADE)
                .productType(ProductType.VESTUARIO)
                .status(Status.ATIVO).build();

    }
}
*/