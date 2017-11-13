package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.parser.fact.IFact;
import com.lfkdsk.justel.parser.fact.goods.Category;
import com.lfkdsk.justel.parser.fact.goods.Item;
import com.lfkdsk.justel.parser.fact.place.Source;
import com.lfkdsk.justel.parser.fact.user.Buyer;
import com.lfkdsk.justel.parser.fact.user.Seller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhulin(fengyuan)
 * @since 17/7/27.
 */
public class OrderLineSpec implements IFact {

    private Seller seller = new Seller() {
        @Override
        public long getSellerId() {
            return 0;
        }

        @Override
        public long getSellerTypeId() {
            return 0;
        }

        @Override
        public long getUserTag() {
            return 0;
        }

        @Override
        public long getUserTag2() {
            return 0;
        }

        @Override
        public long getUserTag3() {
            return 0;
        }

        @Override
        public long getUserTag4() {
            return 0;
        }

        @Override
        public long getUserTag5() {
            return 0;
        }

        @Override
        public long getUserTag6() {
            return 0;
        }

        @Override
        public long getUserTag7() {
            return 0;
        }

        @Override
        public long getUserTag8() {
            return 0;
        }

        @Override
        public long getUserTag9() {
            return 0;
        }

        @Override
        public long getUserTag10() {
            return 0;
        }

        @Override
        public long getUserTag11() {
            return 0;
        }

        @Override
        public long getUserTag12() {
            return 0;
        }

        @Override
        public long getUserTag13() {
            return 0;
        }

        @Override
        public long getUserTag14() {
            return 0;
        }

        @Override
        public long getUserTag15() {
            return 0;
        }

        @Override
        public long getUserTag16() {
            return 0;
        }

        @Override
        public long getUserTag17() {
            return 0;
        }

        @Override
        public long getUserTag18() {
            return 0;
        }

        @Override
        public long getUserTag19() {
            return 0;
        }

        @Override
        public long getUserTag20() {
            return 0;
        }
    };


    /**
     * 获取类目对象
     *
     * @return
     */
    private Category category = new Category() {
        @Override
        public long getCategoryId() {
            return 0;
        }

        @Override
        public long getRootCategoryId() {
            return 0;
        }

        @Override
        public Map<String, String> getCategoryFeatures() {
            return new HashMap<>();
        }
    };

    /**
     * 获取渠道对象
     *
     * @return
     */
    private Source source = new Source() {
        @Override
        public Map<String, String> getReqParam() {
            return new HashMap<>();
        }

        @Override
        public String getPageType() {
            return "ffff";
        }

        @Override
        public boolean isFromWireless() {
            return true;
        }

        @Override
        public boolean isFromPc() {
            return true;
        }

        @Override
        public boolean isH5Client() {
            return true;
        }
    };

    private Buyer buyer = new Buyer() {

        @Override
        public long getUserTag() {
            return 0;
        }

        @Override
        public long getUserTag2() {
            return 0;
        }

        @Override
        public long getUserTag3() {
            return 0;
        }

        @Override
        public long getUserTag4() {
            return 0;
        }

        @Override
        public long getUserTag5() {
            return 0;
        }

        @Override
        public long getUserTag6() {
            return 0;
        }

        @Override
        public long getUserTag7() {
            return 0;
        }

        @Override
        public long getUserTag8() {
            return 0;
        }

        @Override
        public long getUserTag9() {
            return 0;
        }

        @Override
        public long getUserTag10() {
            return 0;
        }

        @Override
        public long getUserTag11() {
            return 0;
        }

        @Override
        public long getUserTag12() {
            return 0;
        }

        @Override
        public long getUserTag13() {
            return 0;
        }

        @Override
        public long getUserTag14() {
            return 0;
        }

        @Override
        public long getUserTag15() {
            return 0;
        }

        @Override
        public long getUserTag16() {
            return 0;
        }

        @Override
        public long getUserTag17() {
            return 0;
        }

        @Override
        public long getUserTag18() {
            return 0;
        }

        @Override
        public long getUserTag19() {
            return 0;
        }

        @Override
        public long getUserTag20() {
            return 0;
        }
    };

    private Item item = new Item() {
        @Override
        public Set<Integer> getItemTags() {
            Set<Integer> tags = new HashSet<>();
            tags.add(12345);
            tags.add(67890);
            return tags;
        }

        @Override
        public Long getItemOptions() {
            return null;
        }

        @Override
        public Map<String, String> getItemFeatures() {
            Map<String, String> features = new HashMap<>();
            features.put("zhulin", "111");
            features.put("fengyuan", "222");
            return features;
        }

        @Override
        public String getAuctionType() {
            return null;
        }

        @Override
        public Long getItemId() {
            return null;
        }

        @Override
        public Integer getStuffStatus() {
            return null;
        }
    };

    @Override
    public LatticeIdentityModel getStandardModel() {
        return new LatticeIdentityModel(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
