package codingdojo;

import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.approvaltests.scrubbers.RegExScrubber;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static codingdojo.SampleModelObjects.*;

public class XMLExporterTest {

  @Test
  public void testExportFullTest() {
    Collection<Order> orders = List.of(RecentOrder, OldOrder);
    String xml = XMLExporter.exportFull(orders);

    Approvals.verifyXml(xml);
  }

  @Test
  public void testExportTaxDetails() {
    List<Order> orders = List.of(RecentOrder, OldOrder);
    String xml = XMLExporter.exportTaxDetails(orders);

    Approvals.verifyXml(xml);
  }

  @Test
  public void testExportStore() {
    Store store = FlagshipStore;
    String xml = XMLExporter.exportStore(store);

    Approvals.verifyXml(xml);
  }

  @Test
  public void testExportHistory() {
    List<Order> orders = List.of(RecentOrder, OldOrder);
    String xml = XMLExporter.exportHistory(orders);
    String regex = "createdAt='[^']+'";
    Approvals.verifyXml(xml, new Options(new RegExScrubber(
        "createdAt=\"[^\"]+\"",
        "createdAt=\"2018-09-20T00:00Z\""
    )));

  }
}

