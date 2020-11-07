using System;
using System.Collections.Generic;
using System.Linq;
using WebApplication.Interfaces;
using WebApplication.Models;

namespace WebApplication.Services
{
    public class FashionRepository : IFashionRepository
    {
        private List<FashionItem> itemList;

        public FashionRepository()
        {
            InitializeDdata(); }

        private void InitializeDdata()
        {
            itemList = new List<FashionItem>();

            var FashionItem1 = new FashionItem()
            {
                Id = "1",
                Type = "kabat",
                Notes = "szep ruha",
                DateOfPurchase = new DateTime(System.DateTime.Now.DayOfYear)
               
            };
            
            itemList.Add(FashionItem1);

        }

        public bool DoesItemExist(string id)
        {
            return itemList.Any(item => item.Id == id);
        }

        public IEnumerable<FashionItem> All
        {
            get { return itemList; }
        }
        
        public FashionItem Find(string id)
        {
            return itemList.FirstOrDefault(item => item.Id == id);
        }

        public void Insert(FashionItem item)
        {
            
            itemList.Add(item);
        }

        public void Update(FashionItem item)
        {
            var todoItem = this.Find(item.Id);
            var index = itemList.IndexOf(todoItem);
            itemList.RemoveAt(index);
            itemList.Insert(index, item);
        }

        public void Delete(string id)
        {
            itemList.Remove(this.Find(id));
        }
    }
}