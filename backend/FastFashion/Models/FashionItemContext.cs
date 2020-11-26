using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace FastFashion.Models
{
    public class FashionItemContext : DbContext
    {
           
        public FashionItemContext(DbContextOptions<FashionItemContext> options)
           : base(options)
        {
        }

        public DbSet<FashionItem> FashionItems { get; set; }
        public DbSet<User> User { get; set; }

    }
}
