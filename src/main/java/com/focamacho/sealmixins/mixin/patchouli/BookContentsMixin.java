package com.focamacho.sealmixins.mixin.patchouli;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.patchouli.client.book.BookCategory;
import vazkii.patchouli.client.book.BookContents;
import vazkii.patchouli.client.book.BookEntry;
import vazkii.patchouli.client.book.ClientBookRegistry;
import vazkii.patchouli.common.book.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

@Mixin(value = BookContents.class, remap = false)
public abstract class BookContentsMixin {

    @Shadow public Map<ResourceLocation, BookEntry> entries;

    @Shadow protected abstract Reader loadLocalizedJson(ResourceLocation res);

    /**
     * @author Seal Mixins
     * @reason Add mod check
     */
    @Overwrite
    private void loadEntry(ResourceLocation key, ResourceLocation res, Book book) {
        String modcheck = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(loadLocalizedJson(res));
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                if (read.contains("\"modcheck\"")) {
                    modcheck = read
                            .replace("\"", "")
                            .replace("modcheck", "")
                            .replace(":", "")
                            .replace(",", "")
                            .replace("\t", "")
                            .replace("\n", "")
                            .replace(" ", "")
                            .replace("{", "")
                            .replace("}", "");
                }
            }
        } catch(Exception ignored) {}
        if(!modcheck.isEmpty() && !Loader.isModLoaded(modcheck)) return;

        try (Reader stream = loadLocalizedJson(res)) {
            BookEntry entry = ClientBookRegistry.INSTANCE.gson.fromJson(stream, BookEntry.class);
            if (entry == null)
                throw new IllegalArgumentException(res + " does not exist.");

            entry.setBook(book);
            if (entry.canAdd()) {
                BookCategory category = entry.getCategory();
                if (category != null)
                    category.addEntry(entry);
                else
                    new RuntimeException("Entry " + key + " does not have a valid category.").printStackTrace();

                this.entries.put(key, entry);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
