package zacseriano.economadworksheets.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import zacseriano.economadworksheets.domain.dto.ExpenseDto;
import zacseriano.economadworksheets.domain.dto.StatisticsDto;
import zacseriano.economadworksheets.domain.enums.StatisticsTypeEnum;
import zacseriano.economadworksheets.domain.form.ExpenseForm;
import zacseriano.economadworksheets.domain.mapper.ExpenseMapper;
import zacseriano.economadworksheets.domain.model.Expense;
import zacseriano.economadworksheets.domain.model.Origin;
import zacseriano.economadworksheets.service.expense.ExpenseService;
import zacseriano.economadworksheets.service.origin.OriginService;
import zacseriano.economadworksheets.specification.filter.ExpenseFilter;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
	@Autowired
	private ExpenseService service;
	@Autowired
	private ExpenseMapper mapper;
	@Autowired
	private OriginService originService;

	@GetMapping
	public ResponseEntity<Page<ExpenseDto>> listAll(
			@PageableDefault(size = 20, sort = "date", direction = Sort.Direction.DESC) Pageable pageable, 
			ExpenseFilter filtro) {

		Page<Expense> expenses = service.listAll(filtro, pageable);
		Page<ExpenseDto> expensesDto = expenses.map(this.mapper::toDto);

		return ResponseEntity.ok(expensesDto);
	}

	@GetMapping("/statistics")
	public ResponseEntity<List<StatisticsDto>> statistics(
			@RequestParam(required = true) String billingMonth,
			@RequestParam(required = true) StatisticsTypeEnum statisticsType) {

		List<StatisticsDto> statisticsDto = service.listStatisticsByMonth(billingMonth, statisticsType);

		return ResponseEntity.ok(statisticsDto);
	}
	
	@GetMapping("/generate-monthly-worksheet")
	public ResponseEntity<byte[]> generateMonthlyWorksheet(@RequestParam(required = true) String billingMonth) throws IOException {
        byte[] worksheetBytes = service.generateMonthlyWorksheet(billingMonth);
        HttpHeaders headers = new HttpHeaders();
        String billingMonthWithoutSlash = billingMonth.replaceAll("/", "");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "monthly_worksheet_" + billingMonthWithoutSlash + ".xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(worksheetBytes);
    }
	
	@GetMapping("/list-orgins")
	public ResponseEntity<List<String>> listarOrigens() {
		List<Origin> origins = originService.listAll();
		List<String> names = origins.stream().map(r -> r.getName()).toList();		
		return ResponseEntity.ok(names);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ExpenseDto> create(@RequestBody @Valid ExpenseForm form, UriComponentsBuilder uriBuilder) {
		Expense expense = service.create(form);
		ExpenseDto expenseDto = mapper.toDto(expense);
		return ResponseEntity.ok(expenseDto);
	}
	
	@GetMapping("/calculate-rdi")
	public ResponseEntity<BigDecimal> calculateRelativeDailyIndex(@RequestParam(required = true) LocalDate initialDate,
			@RequestParam(required = true) LocalDate finalDate) {		
		return ResponseEntity.ok(service.calculateRelativeDailyIndex(initialDate, finalDate));
	}

}
