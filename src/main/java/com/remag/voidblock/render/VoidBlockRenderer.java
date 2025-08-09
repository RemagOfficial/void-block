package com.remag.voidblock.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.remag.voidblock.block.entities.VoidBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.core.Direction;
import org.joml.Matrix4f;

public class VoidBlockRenderer implements BlockEntityRenderer<VoidBlockEntity> {

    public VoidBlockRenderer(BlockEntityRendererProvider.Context context) {
        // No superclass, nothing to call here
    }

    @Override
    public void render(VoidBlockEntity entity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        Matrix4f matrix = poseStack.last().pose();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(this.renderType());

        renderCube(entity, matrix, vertexConsumer);
    }

    private void renderCube(VoidBlockEntity entity, Matrix4f matrix, VertexConsumer vertexConsumer) {
        float offsetDown = this.getOffsetDown(); // usually 0.0f
        float offsetUp = this.getOffsetUp();     // usually 1.0f

        renderFace(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, offsetDown, offsetDown, Direction.DOWN);
        renderFace(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, offsetUp, offsetUp, Direction.UP);

        renderFace(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, Direction.SOUTH);
        renderFace(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, Direction.NORTH);

        renderFace(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, Direction.EAST);
        renderFace(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, Direction.WEST);
    }

    private void renderFace(VoidBlockEntity entity, Matrix4f matrix, VertexConsumer vertexConsumer,
                            float uStart, float uEnd, float vStart, float vEnd,
                            float xStart, float xEnd, float yStart, float yEnd,
                            Direction face) {
        if (!shouldRenderFace(entity, face)) return;

        switch (face) {
            case SOUTH: // z = 1.0
                vertexConsumer.vertex(matrix, xStart, yStart, 1.0F).uv(uStart, vStart).endVertex();
                vertexConsumer.vertex(matrix, xEnd, yStart, 1.0F).uv(uEnd, vStart).endVertex();
                vertexConsumer.vertex(matrix, xEnd, yEnd, 1.0F).uv(uEnd, vEnd).endVertex();
                vertexConsumer.vertex(matrix, xStart, yEnd, 1.0F).uv(uStart, vEnd).endVertex();
                break;

            case NORTH: // z = 0.0
                vertexConsumer.vertex(matrix, xEnd, yStart, 0.0F).uv(uEnd, vStart).endVertex();
                vertexConsumer.vertex(matrix, xStart, yStart, 0.0F).uv(uStart, vStart).endVertex();
                vertexConsumer.vertex(matrix, xStart, yEnd, 0.0F).uv(uStart, vEnd).endVertex();
                vertexConsumer.vertex(matrix, xEnd, yEnd, 0.0F).uv(uEnd, vEnd).endVertex();
                break;

            case EAST: // x = 1.0; vary Y and Z
                vertexConsumer.vertex(matrix, 1.0F, yStart, yEnd).uv(uEnd, vStart).endVertex();
                vertexConsumer.vertex(matrix, 1.0F, yStart, yStart).uv(uStart, vStart).endVertex();
                vertexConsumer.vertex(matrix, 1.0F, yEnd, yStart).uv(uStart, vEnd).endVertex();
                vertexConsumer.vertex(matrix, 1.0F, yEnd, yEnd).uv(uEnd, vEnd).endVertex();
                break;

            case WEST: // x = 0.0; vary Y and Z
                vertexConsumer.vertex(matrix, 0.0F, yStart, yStart).uv(uStart, vStart).endVertex();
                vertexConsumer.vertex(matrix, 0.0F, yStart, yEnd).uv(uEnd, vStart).endVertex();
                vertexConsumer.vertex(matrix, 0.0F, yEnd, yEnd).uv(uEnd, vEnd).endVertex();
                vertexConsumer.vertex(matrix, 0.0F, yEnd, yStart).uv(uStart, vEnd).endVertex();
                break;

            case UP: // y = offsetUp; vary X and Z
                vertexConsumer.vertex(matrix, xStart, yStart, 1.0F).uv(uStart, vStart).endVertex();
                vertexConsumer.vertex(matrix, xEnd, yStart, 1.0F).uv(uEnd, vStart).endVertex();
                vertexConsumer.vertex(matrix, xEnd, yStart, 0.0F).uv(uEnd, vEnd).endVertex();
                vertexConsumer.vertex(matrix, xStart, yStart, 0.0F).uv(uStart, vEnd).endVertex();
                break;

            case DOWN: // y = offsetDown; vary X and Z
                vertexConsumer.vertex(matrix, xStart, yEnd, 0.0F).uv(uStart, vStart).endVertex();
                vertexConsumer.vertex(matrix, xEnd, yEnd, 0.0F).uv(uEnd, vStart).endVertex();
                vertexConsumer.vertex(matrix, xEnd, yEnd, 1.0F).uv(uEnd, vEnd).endVertex();
                vertexConsumer.vertex(matrix, xStart, yEnd, 1.0F).uv(uStart, vEnd).endVertex();
                break;
        }
    }

    private boolean shouldRenderFace(VoidBlockEntity entity, Direction face) {
        // You can customize which faces to render here.
        // For now, render all faces:
        return true;
    }

    protected float getOffsetUp() {
        return 1.0F;
    }

    protected float getOffsetDown() {
        return 0.0F;
    }

    protected RenderType renderType() {
        return RenderType.endGateway();
    }
}
