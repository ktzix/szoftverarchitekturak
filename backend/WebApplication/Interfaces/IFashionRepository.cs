using System.Collections.Generic;
using WebApplication.Models;

namespace WebApplication.Interfaces
{
    public interface IFashionRepository
    {
        bool DoesItemExist(string id);
        IEnumerable<FashionItem> All { get; }
        FashionItem Find(string id);
        void Insert(FashionItem item);
        void Update(FashionItem item);
        void Delete(string id);
    }
    }
