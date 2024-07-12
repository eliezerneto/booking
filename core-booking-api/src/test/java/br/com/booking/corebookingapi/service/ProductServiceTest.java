package br.com.booking.corebookingapi.service;
/*
import br.com.booking.corebookingapi.enums.Status;
import br.com.booking.corebookingapi.exception.ApiExceptionHandler;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    MessageSource messageSource;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductMapper productMapper;
    @InjectMocks
    private ProductService productService;

    @InjectMocks
    private ApiExceptionHandler apiExceptionHandler;

    @Test
    void givenValidParameter_whenCreateProduct_thenPersistProduct() {

        when(productMapper.toEntity(any(CreateUpdateProductDTO.class))).thenReturn(productDefault());
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTODefault());

        productService.createProduct(createUpdateProductDTODefault());

        ArgumentCaptor<Product> argumentCaptorHistory = ArgumentCaptor.forClass(Product.class);

        verify(productRepository, times(1)).save(argumentCaptorHistory.capture());

        Product product = argumentCaptorHistory.getValue();
        assertEquals("Teste", product.getName());
        assertEquals(ProductType.VESTUARIO, product.getProductType());
        assertEquals("9780201379624", product.getBarCode());
    }

    public static Product productDefault() {

        return Product.builder()
                .id(1L)
                .name("Teste")
                .description("Descrição")
                .barCode("9780201379624")
                .unitMeasurement(UnitMeasurement.UNIDADE)
                .productType(ProductType.VESTUARIO)
                .status(Status.ATIVO).build();

    }

    public static CreateUpdateProductDTO createUpdateProductDTODefault() {

        return CreateUpdateProductDTO.builder()
                .name("Teste")
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