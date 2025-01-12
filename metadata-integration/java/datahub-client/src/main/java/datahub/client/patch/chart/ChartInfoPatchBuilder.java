package datahub.client.patch.chart;

import static com.linkedin.metadata.Constants.*;
import static datahub.client.patch.common.PatchUtil.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linkedin.common.urn.Urn;
import datahub.client.patch.AbstractMultiFieldPatchBuilder;
import datahub.client.patch.PatchOperationType;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.tuple.ImmutableTriple;

public class ChartInfoPatchBuilder extends AbstractMultiFieldPatchBuilder<ChartInfoPatchBuilder> {
  private static final String INPUT_EDGES_PATH_START = "/inputEdges/";

  // Simplified with just Urn
  public ChartInfoPatchBuilder addInputEdge(@Nonnull Urn urn) {
    ObjectNode value = createEdgeValue(urn);

    pathValues.add(
        ImmutableTriple.of(PatchOperationType.ADD.getValue(), INPUT_EDGES_PATH_START + urn, value));
    return this;
  }

  public ChartInfoPatchBuilder removeInputEdge(@Nonnull Urn urn) {
    pathValues.add(
        ImmutableTriple.of(
            PatchOperationType.REMOVE.getValue(), INPUT_EDGES_PATH_START + urn, null));
    return this;
  }

  @Override
  protected String getAspectName() {
    return CHART_INFO_ASPECT_NAME;
  }

  @Override
  protected String getEntityType() {
    return CHART_ENTITY_NAME;
  }
}
